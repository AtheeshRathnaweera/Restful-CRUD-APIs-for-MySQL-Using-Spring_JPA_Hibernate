package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.StudentForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.StudentHistory;
import com.main.sqlcrud.model.User;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.StudentHistoryRepository;
import com.main.sqlcrud.repository.StudentRepository;
import com.main.sqlcrud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studentMg/student")
public class StudentAPIs {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentHistoryRepository studentHistoryRepository;

    @PostMapping("/add") // Operators only
    public Student addingUser(@Valid @RequestBody StudentForm studentRequest) {

        Student recResult = null;
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (!studentRepository.existsByAdmissionNumber(studentRequest.getAdmissionNumber())) {

            StudentHistory studentHistory = studentHistoryRepository.findByAdmissionNumber(studentRequest.getAdmissionNumber());
            int currentClassId = studentRequest.getCurrentClassId();

            // Get the Class details
            SClass temp = null;
            List<SClass> tempClass = new ArrayList<>();

            if(currentClassId != 0){
                //save student if a class exists
                temp = classRepository.findClassById(currentClassId);
                tempClass.add(temp);
                recResult = studentRepository.save(new Student(studentRequest.getAdmissionNumber(),
                    studentRequest.getFirstName(), studentRequest.getLastName(), studentRequest.getBday(),
                    studentRequest.getAddress(), studentRequest.getEnrolledDate(), temp, studentRequest.getStatus()));
            }else{
                tempClass = classRepository.findByGrade(0);
                recResult = studentRepository.save(new Student(studentRequest.getAdmissionNumber(),
                    studentRequest.getFirstName(), studentRequest.getLastName(), studentRequest.getBday(),
                    studentRequest.getAddress(), studentRequest.getEnrolledDate(), tempClass.get(0), studentRequest.getStatus()));

            }

            if(studentHistory == null){
                System.out.println("student history is null : "+recResult);
                studentHistoryRepository.save(new StudentHistory(studentRequest.getAdmissionNumber(),year,tempClass.get(0)));
            }else{
                if(studentHistory.getYear() != year){
                    studentHistoryRepository.save(new StudentHistory(studentRequest.getAdmissionNumber(),year,tempClass.get(0)));
                }
            }

            System.out.println("save return : " + recResult.toString());

        }

        System.out.println("done : "+recResult);

        return recResult;

    }

    @PutMapping("/update") // student pro
    public Student updateAStudent(@Valid @RequestBody StudentForm student) {

        Student temp = studentRepository.findByAdmissionNumber(student.getAdmissionNumber());

        Student recStudent = new Student(); // store the updated student
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (temp != null) {
            StudentHistory studentHistory = studentHistoryRepository
                    .findByAdmissionNumber(student.getAdmissionNumber());
            SClass currentClass = classRepository.findClassById(student.getCurrentClassId());

            if (year != studentHistory.getYear()) {
                studentHistoryRepository.save(new StudentHistory(student.getAdmissionNumber(), year, currentClass));
            }

            recStudent = studentRepository.save(new Student(student.getAdmissionNumber(),student.getFirstName(), student.getLastName(), student.getBday(),
                            student.getAddress(), student.getEnrolledDate(), currentClass, student.getStatus()));

            return recStudent;

        } else {
            return recStudent;
        }
    }

    @DeleteMapping("/remove/{admissionNumber}")
    public Boolean removeStudent(@PathVariable(value = "admissionNumber") Long admissionNumber) {
        Student temp = studentRepository.findByAdmissionNumber(admissionNumber);

        if (temp != null) {
            userRepository.delete(new User(Long.toString(admissionNumber)));
            temp.setStatus("blocked");
            studentRepository.save(temp);
            return true;
        } else {
            // student not exist
            return false;
        }

    }

    @PutMapping("/reassign/{admissionNum}")
    public Student reassignTeacher(@PathVariable(value = "admissionNum") String admissionNum) {
        Student existStudent = studentRepository.findByAdmissionNumber(Long.parseLong(admissionNum));
        Student updatedStudent = null;

        if (!existStudent.equals(null)) {
            existStudent.setStatus("active");
            updatedStudent = studentRepository.save(existStudent);
        }

        return updatedStudent;
    }

    @GetMapping("/get/{admissionNum}") // get student by admission number
    public Student getStudentByAdmissionNum(@PathVariable(value = "admissionNum") String admissionNumber) {

        long number = new Long(admissionNumber).longValue();
        return studentRepository.findByAdmissionNumber(number);

    }

    // GET ALL STUDENTS COUNT
    @GetMapping("/getallcount")
    public Long getAllStudentsCount() {

        return studentRepository.count();

    }

    // GET ALL STUDENTS
    @GetMapping("/getall")
    public List<Student> getAllStudents() {

        return studentRepository.findAll();

    }

    @GetMapping("/getallstudentbyclass/{id}")
    public List<Student> getAllStudentsByClass(@PathVariable(value = "id") String id) {

        return studentRepository.findByCurrentClass(new SClass(Integer.parseInt(id)));

    }

    // GET STUDENT BY STATUS
    @GetMapping("/getbystatus/{status}")
    public List<Student> getByStatus(@PathVariable(value = "status") String status) {

        return studentRepository.findByStatus(status);

    }

}