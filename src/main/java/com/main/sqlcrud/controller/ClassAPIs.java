package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.AssignTeacherToClassForm;
import com.main.sqlcrud.message.request.AssignToClassForm;
import com.main.sqlcrud.message.request.NewClassForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.StudentHistory;
import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.StudentHistoryRepository;
import com.main.sqlcrud.repository.StudentRepository;
import com.main.sqlcrud.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studentMg/class")
public class ClassAPIs {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentHistoryRepository studentHistoryRepository;

    @PostMapping("/add") // Operators only
    public SClass addingClass(@Valid @RequestBody NewClassForm newClassRequest) {

        SClass tempClass = new SClass(newClassRequest.getGrade(), newClassRequest.getName());

        ArrayList<?> result = classRepository.getClassIdByGradeAndName(newClassRequest.getGrade(),
                newClassRequest.getName());

        if (result.isEmpty()) {
            System.out.println("class not exists");
            SClass savedClass = classRepository.save(tempClass);
            return savedClass;

        } else {
            System.out.println("class exists.");
            return null;
        }

    }

    // get all classes count
    @GetMapping("/allCount")
    public Long getAllClassesCount() {
        return classRepository.count();

    }

    //get all classes
    @GetMapping("/getAll")
    public List<SClass> getAllClasses(){
        return classRepository.findAll();
    }

    // Get class data by id
    @GetMapping("/getInfo/{classId}")
    public SClass gettingClassesInfo(@PathVariable(value = "classId") int classId) {
        SClass result = classRepository.findClassById(classId);
        return result;
    }

    // Get all classes of a grade
    @GetMapping("/getClasses/{grade}")
    public List<SClass> gettingClasses(@PathVariable(value = "grade") int grade) {
        List<SClass> result = classRepository.findByGrade(grade);
        System.out.println("result : " + result.size());
        return result;
    }

    // Get all classes amount of a grade
    @GetMapping("/getClassAmount/{grade}")
    public Long gettingClassAmountInGrade(@PathVariable(value = "grade") int grade) {
        Long result = classRepository.getClassAmountOfAGrade(grade);
        return result;

    }

    // Get students of a class
    @GetMapping("/getStudents/{classId}")
    public List<Student> gettingStudents(@PathVariable(value = "classId") int classId) {
        List<Student> result = studentRepository.getStudentsInAClass(classId);
        System.out.println("result size : " + result.size());
        return result;
    }

    // Get students count of a class
    @GetMapping("/getStudentsCount/{classId}")
    public Long gettingStudentsCount(@PathVariable(value = "classId") int classId) {
        return studentRepository.getStudentsCountInAClass(classId);

    }

    // Get class teacher
    @GetMapping("/getTeacher/{classId}")
    public List<Teachers> getClassTeacher(@PathVariable(value = "classId") int classId) {
        List<Teachers> result = teacherRepository.getTeacherByClass(classId);
        return result;
    }

    @PutMapping("/assignStudents")
    public List<Student> assignStudentsToTheClass(@Valid @RequestBody AssignToClassForm assignData) {
        List<Student> updatedStudentsList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR); // get current year

        for (int i = 0; i < assignData.getStudentsAds().length; i++) {
            String studentAdId = assignData.getStudentsAds()[i];

            Student existStudent = studentRepository.findByAdmissionNumber(Long.parseLong(studentAdId));
            StudentHistory studentHistoryYear = studentHistoryRepository.getHistoryByYearAndAdmissionNum(year,
                    Long.parseLong(studentAdId));

            Student updatedStudent = null;

            SClass tempClass = classRepository.findClassById(Integer.parseInt(assignData.getClassId()));

            if (!existStudent.equals(null) && !tempClass.equals(null)) {

                existStudent.setStatus("active");
                existStudent.setCurrentClass(tempClass);
                updatedStudent = studentRepository.save(existStudent);
                updatedStudentsList.add(updatedStudent);

                if (studentHistoryYear == null) {
                    studentHistoryRepository.save(new StudentHistory(Long.parseLong(studentAdId), Integer.parseInt(assignData.getClassId()), tempClass));
                } else {
                    studentHistoryYear.setClassId(tempClass);
                    studentHistoryRepository.save(studentHistoryYear);
                }
            }

        }

        return updatedStudentsList;
    }

    @PutMapping("/assignTeacher")
    public Teachers assignTeacherToTheClass(@Valid @RequestBody AssignTeacherToClassForm assignData) {
        System.out.println("rec data : " + assignData.toString());
        Teachers updatedTeacher = null;

        SClass existClass = classRepository.findClassById(Integer.parseInt(assignData.getClassId()));

        Teachers newTeacher = teacherRepository.findByNic(assignData.getNewTeacherNic());

        if (!existClass.equals(null) && !newTeacher.equals(null)) {
            newTeacher.setCurrentClass(existClass);
            if (!assignData.getOldTeacherNic().equals("not found")) {
                Teachers oldTeacher = teacherRepository.findByNic(assignData.getOldTeacherNic());
                oldTeacher.setCurrentClass(new SClass(1));
                teacherRepository.save(oldTeacher);
            }

            updatedTeacher = teacherRepository.save(newTeacher);
        }

        return updatedTeacher;
    }

    @PutMapping("/removeStudents")
    public List<Student> removeStudentsFromTheClass(@Valid @RequestBody AssignToClassForm assignData) {
        List<Student> updatedStudentsList = new ArrayList<>();

        for (int i = 0; i < assignData.getStudentsAds().length; i++) {
            String studentAdId = assignData.getStudentsAds()[i];
            Student existStudent = studentRepository.findByAdmissionNumber(Long.parseLong(studentAdId));
            Student updatedStudent = null;

            if (!existStudent.equals(null)) {
                existStudent.setCurrentClass(new SClass(1));
                updatedStudent = studentRepository.save(existStudent);
                updatedStudentsList.add(updatedStudent);
            }

        }

        return updatedStudentsList;
    }

}