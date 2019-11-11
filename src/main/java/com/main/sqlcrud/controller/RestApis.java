package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.NewClassForm;
import com.main.sqlcrud.message.request.StudentForm;
import com.main.sqlcrud.message.request.TeacherForm;
import com.main.sqlcrud.message.response.ResponseMessage;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.model.User;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.StudentRepository;
import com.main.sqlcrud.repository.TeacherRepository;
import com.main.sqlcrud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/studentMg")
public class RestApis {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserRepository userRepository;

    // Login api
    @PostMapping("/login")
    public User logginUser(@Valid @RequestBody User userLogRequest) {

        System.out.println("Rec user details : (login) " + userLogRequest.toString());

        User temp = userRepository.findByUserId(userLogRequest.getUserId());
        User response = new User();

        System.out.println("Found user : " + temp.toString());

        if (temp != null) {
            if (temp.getPassword().equals(userLogRequest.getPassword())) {
                System.out.println("Password matched");
                response.setUserId(temp.getUserId());
                response.setUserRole(temp.getUserRole());
            }else{
                System.out.println("Password mismatched.");
            }

        }

        return response;

    }
    // Login api

    // Sigin apis
    @PostMapping("/signin/student")
    public User signinStudent(@Valid @RequestBody User userRequest) {

        User response = new User();

        if (studentRepository.existsByAdmissionNumber(Long.parseLong(userRequest.getUserId()))) { // Student use
                                                                                                  // admission number as
                                                                                                  // their user ID

                                                                                                  
            if (!userRepository.existsById(userRequest.getUserId())) {
                User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
                userRepository.save(newUser);
                response.setUserId(newUser.getUserId());
                response.setUserRole(newUser.getUserRole());
            }else{
                System.out.println("User id already exists.");
            }

        } else {
            System.out.println("User id not exists in students table.");

        }

        return response;

    }

    @PostMapping("/signin/teacher")
    public User signinTeacher(@Valid @RequestBody User userRequest) {

        User response = new User();

        if (teacherRepository.existsByNic(userRequest.getUserId())) { // Teachers use NIC as their id
                                                                                                  
            if (!userRepository.existsById(userRequest.getUserId())) {
                User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
                userRepository.save(newUser);
                response.setUserId(newUser.getUserId());
                response.setUserRole(newUser.getUserRole());
            }else{
                System.out.println("User id already exists.");
            }

        } else {
            System.out.println("User id not exists in teachers table.");

        }

        return response;

    }
    
    //Signin apis

    //Students,Teachers adding apis (ADMIN only)
    @PostMapping("/addStudent")
    public ResponseEntity<?> addingUser(@Valid @RequestBody StudentForm studentRequest) {

        if (studentRepository.existsByAdmissionNumber(studentRequest.getAdmissionNumber())) {
            return new ResponseEntity<>(new ResponseMessage("Failed -> user is already registered!"),
                    HttpStatus.BAD_REQUEST);
        }

        int currentClassId = studentRequest.getCurrentClassId();
        
        //Get the Class details
        SClass temp = classRepository.findClassById(currentClassId);

        //Create the student object
        Student student = new Student(studentRequest.getAdmissionNumber(), studentRequest.getFirstName(),
                studentRequest.getLastName(), studentRequest.getBday(), studentRequest.getAddress(),
                studentRequest.getEnrolledDate(),temp);

        studentRepository.save(student);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("/addTeacher")
    public ResponseEntity<?> addingTeacher(@Valid @RequestBody TeacherForm newTeacher) {

        if (teacherRepository.existsById(newTeacher.getNIC())) {
            return new ResponseEntity<>(new ResponseMessage("Failed -> teacher is already registered!"),
                    HttpStatus.BAD_REQUEST);
        }

        int currentClassId = newTeacher.getCurrentClassId();

        //Get the Class details
        SClass temp = classRepository.findClassById(currentClassId);

        Teachers tempTeacher = new Teachers(newTeacher.getNIC(), newTeacher.getFirstName(), newTeacher.getLastName(),
                newTeacher.getAddress(), newTeacher.getTelephoneNumber(),newTeacher.getStatus(),temp);
        teacherRepository.save(tempTeacher);

        return new ResponseEntity<>(new ResponseMessage("Teacher added successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("/addClass")
    public ResponseEntity<?> addingClass(@Valid @RequestBody NewClassForm newClassRequest) {

        SClass tempClass = new SClass(newClassRequest.getGrade(), newClassRequest.getName());

        ArrayList<?> result = classRepository.getClassIdByGradeAndName(newClassRequest.getGrade(),newClassRequest.getName());

    
        if(result.isEmpty()){
            System.out.println("class not exists");
            classRepository.save(tempClass);
            return new ResponseEntity<>(new ResponseMessage("class added successfully!"), HttpStatus.CREATED);
           
        }else{
            System.out.println("class exists.");
            return new ResponseEntity<>(new ResponseMessage("class added failed!"), HttpStatus.BAD_REQUEST);
        }

    }
    //Students,Teachers, Class adding apis (ADMIN only)

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateAStudent(@Valid @RequestBody StudentForm student) {

        Student temp = studentRepository.findByAdmissionNumber(student.getAdmissionNumber());

        if (temp != null) {
            temp.setFirstName(student.getFirstName());
            temp.setLastName(student.getLastName());
            temp.setBday(student.getBday());
            temp.setAddress(student.getAddress());
            // temp.setAdmissionNumber(admissionNumber);
            temp.setEnrolledDate(student.getEnrolledDate());

            studentRepository.save(temp);

            return new ResponseEntity<>(new ResponseMessage("Updated successfully!"), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ResponseMessage("Student not found under this admission number"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteStudent/{admissionNumber}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "admissionNumber") Long admissionNumber) {
        Student temp = studentRepository.findByAdmissionNumber(admissionNumber);

        if (temp != null) {
            studentRepository.delete(temp);
            return new ResponseEntity<>(new ResponseMessage("deleted successfully!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Student not found under this admission number"),
                    HttpStatus.BAD_REQUEST);
        }

    }
   

    //Get students from the db
    
    @GetMapping("/getStudent/{admissionNum}") // get student by admission number
    public Student gettingUser(@PathVariable(value = "admissionNum") String admissionNumber) {
        
        long number = new Long(admissionNumber).longValue();
        System.out.println("admission id : " + number);
        return studentRepository.findByAdmissionNumber(number);

    }
    //get students from the db

    //get teacher details
    @GetMapping("/getTeacher/{teacherNic}") // get teacher details in the class
    public Teachers getTeacherDetails(@PathVariable(value = "teacherNic") String teacherNic) {

        return teacherRepository.findByNic(teacherNic);

    }

    //Get students count
    @GetMapping("/users/studentUsersCount")
    public ArrayList<?> gettingStudentUsersCount() {
        return userRepository.getStudentUsersCount();
    }

    //Get teachers count
    @GetMapping("/users/teacherUsersCount")
    public ArrayList<Long> gettingTeacherUsersCount() {
        return userRepository.getTeacherUsersCount();
    }

    //Get all classes of a grade
    @GetMapping("/classes/getClasses/{grade}")
    public List<SClass> gettingClasses(@PathVariable(value = "grade") int grade) {
        List<SClass> result = classRepository.findByGrade(grade);
        System.out.println("result : "+result.size());
        return result;
    }

    //Get students of a class
    @GetMapping("/classes/getStudents/{classId}")
    public List<Student> gettingStudents(@PathVariable(value = "classId") int classId) {
        List<Student> result = studentRepository.getStudentsInAClass(classId);
        System.out.println("result : "+result.size()+" "+ result.toString());
        return result;
    }




}