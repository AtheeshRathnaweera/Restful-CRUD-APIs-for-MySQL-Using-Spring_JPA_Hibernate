package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.validation.Valid;

import com.main.sqlcrud.model.Operator;
import com.main.sqlcrud.model.Student;
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

        User temp = userRepository.findByUserId(userLogRequest.getUserId());
        User response = new User();

        if (temp != null) {
            if (temp.getPassword().equals(userLogRequest.getPassword())) {
                System.out.println("Password matched");
                response.setUserId(temp.getUserId());
                response.setUserRole(temp.getUserRole());
                response.setPassword(temp.getPassword());
            } else {
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

        if (userRequest.getUserRole().equals("student")
                && studentRepository.existsByAdmissionNumber(Long.parseLong(userRequest.getUserId()))
                && !userRepository.existsById(userRequest.getUserId())) { // Student use

            User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
            response = userRepository.save(newUser);
        }

        return response;

    }

    @PostMapping("/signin/teacher")
    public User signinTeacher(@Valid @RequestBody User userRequest) {

        User response = new User();
        Teachers teacher = teacherRepository.findByNic(userRequest.getUserId());

        if (!teacher.getStatus().equals("removed") && !userRequest.getUserRole().equals(null)) {

            if (!teacher.equals(null) && !userRepository.existsById(userRequest.getUserId())) {
                User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
                response = userRepository.save(newUser);
            }

        }

        return response;

    }

    @PostMapping("/signin/operator")
    public User signinOperator(@Valid @RequestBody User userRequest) {

        User response = new User();

        System.out.println("received data : "+userRequest.toString());

        if (userRequest.getUserRole().equals("operator") && operatorRepository.existsByNic(userRequest.getUserId())
                && !userRepository.existsById(userRequest.getUserId())) {

            System.out.println("operator found !");

            Operator tempOper = operatorRepository.findByNic(userRequest.getUserId());

            if (tempOper.getStatus().equals("active")) {
                System.out.println("active user found !");
                User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
                response = userRepository.save(newUser);
            }
        }

        return response;

    }

    // Get student users count
    @GetMapping("/users/studentUsersCount")
    public Long gettingStudentUsersCount() {
        List<Long> c = userRepository.getStudentUsersCount();
        return c.get(0);
    }

    // Get teacher users count
    @GetMapping("/users/teacherUsersCount")
    public Long gettingTeacherUsersCount() {
        List<Long> c = userRepository.getTeacherUsersCount();
        return c.get(0);
    }

    @GetMapping("/users/students")
    public List<User> getStudentUsers() {
        return userRepository.findByUserRole("student");
    }

    @GetMapping("/users/teachers")
    public List<User> getTeacherUsers() {
        return userRepository.findByUserRole("teacher");
    }

    @PutMapping("/pwResetAdmin") // pw reset by only for admin
    public Boolean pwResetAdmin(@Valid @RequestBody User userForm) {
        if (userRepository.existsById(userForm.getUserId())) {
            User tempUser = new User(userForm.getUserId(), userForm.getPassword(), userForm.getUserRole());
            userRepository.save(tempUser);

            return true;
        } else {
            return false;
        }
    }

}