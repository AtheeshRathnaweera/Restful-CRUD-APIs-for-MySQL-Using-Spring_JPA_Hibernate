package com.main.sqlcrud.controller;

import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.StudentForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.User;
import com.main.sqlcrud.repository.ClassRepository;
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
public class StudentAPIs{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add") //Operators only
    public Boolean addingUser(@Valid @RequestBody StudentForm studentRequest) {

        Student recResult = null;
        Boolean response = false;

        if (!studentRepository.existsByAdmissionNumber(studentRequest.getAdmissionNumber())) {
            int currentClassId = studentRequest.getCurrentClassId();
        
            //Get the Class details
            SClass temp = classRepository.findClassById(currentClassId);
    
            //Create the student object
            Student student = new Student(studentRequest.getAdmissionNumber(), studentRequest.getFirstName(),
                    studentRequest.getLastName(), studentRequest.getBday(), studentRequest.getAddress(),
                    studentRequest.getEnrolledDate(),temp,studentRequest.getStatus());
    
            recResult = studentRepository.save(student);
    
            System.out.println("save return : "+recResult.toString());

        }

        if(recResult != null){
            response = true;
        }

        return response;
   
  
    }

    @PutMapping("/update") //
    public Student updateAStudent(@Valid @RequestBody StudentForm student) {

        Student temp = studentRepository.findByAdmissionNumber(student.getAdmissionNumber());
        Student recStudent = new Student();

        if (temp != null) {
            temp.setFirstName(student.getFirstName());
            temp.setLastName(student.getLastName());
            temp.setBday(student.getBday());
            temp.setAddress(student.getAddress());
            temp.setEnrolledDate(student.getEnrolledDate());
            temp.setStatus(student.getStatus());

            recStudent= studentRepository.save(temp);

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
            //student not exist
            return false;
        }

    }

    @PutMapping("/reassign/{admissionNum}")
    public Student reassignTeacher(@PathVariable(value="admissionNum") String admissionNum){
        Student existStudent = studentRepository.findByAdmissionNumber(Long.parseLong(admissionNum));
        Student updatedStudent = null;

        if(!existStudent.equals(null)){
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

    //GET ALL STUDENTS COUNT
    @GetMapping("/getallcount")
    public Long getAllStudentsCount() {

        return studentRepository.count();

    }

    //GET ALL STUDENTS
    @GetMapping("/getall")
    public List<Student> getAllStudents() {

        return studentRepository.findAll();

    }

    @GetMapping("/getallstudentbyclass/{id}")
    public List<Student> getAllStudentsByClass(@PathVariable(value="id") String id) {

        return studentRepository.findByCurrentClass(new SClass(Integer.parseInt(id)));

    }





}