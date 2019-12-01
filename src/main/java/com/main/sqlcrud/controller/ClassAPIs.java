package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.NewClassForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.StudentRepository;
import com.main.sqlcrud.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    //get all classes
    @GetMapping("/allCount")
    public Long getAllClasses(){
        return classRepository.count();

    }

    // Get all classes of a grade
    @GetMapping("/getClasses/{grade}")
    public List<SClass> gettingClasses(@PathVariable(value = "grade") int grade) {
        List<SClass> result = classRepository.findByGrade(grade);
        System.out.println("result : " + result.size());
        return result;
    }

    //Get all classes amount of a grade
    @GetMapping("/getClassAmount/{grade}")
    public Long gettingClassAmountInGrade(@PathVariable(value = "grade") int grade){
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

}