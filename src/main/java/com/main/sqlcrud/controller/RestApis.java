package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.validation.Valid;


import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.model.User;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.OperatorRepository;
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

    @Autowired
    OperatorRepository operatorRepository;

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

        if (!userRequest.getUserRole().equals(null) && studentRepository.existsByAdmissionNumber(Long.parseLong(userRequest.getUserId())) && !userRepository.existsById(userRequest.getUserId())) { // Student use
                                                                                                  
                User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
                response = userRepository.save(newUser);
        } 

        return response;

    }

    @PostMapping("/signin/teacher")
    public User signinTeacher(@Valid @RequestBody User userRequest) {

        User response = new User();

        if (!userRequest.getUserRole().equals(null) && teacherRepository.existsByNic(userRequest.getUserId()) && !userRepository.existsById(userRequest.getUserId())) { // Teachers use NIC as their id
            User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
            response = userRepository.save(newUser);
        }
        return response;

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

 

    




}