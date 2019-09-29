package com.main.sqlcrud.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.StudentForm;
import com.main.sqlcrud.message.response.ResponseMessage;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studentMg")
public class RestApis{

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addingUser(@Valid @RequestBody StudentForm studentRequest) {

        if (studentRepository.existsByAdmissionNumber(studentRequest.getAdmissionNumber())) {
			return new ResponseEntity<>(new ResponseMessage("Failed -> student is already registered!"),
					HttpStatus.BAD_REQUEST);
		}

        //System.out.println("received : "
        //    +studentRequest.getFirstName()+" "+studentRequest.getLastName()+"  "+studentRequest.getBday()+"  "
        //    +studentRequest.getAddress()+" "+studentRequest.getAdmissionNumber()+"  "+studentRequest.getEnrolledDate());

        // Creating user's account
        Student student = new Student(studentRequest.getFirstName(),studentRequest.getLastName(),studentRequest.getBday(),studentRequest.getAddress(),studentRequest.getAdmissionNumber(),studentRequest.getEnrolledDate());
        studentRepository.save(student);
		//User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
			//	encoder.encode(signUpRequest.getPassword()));

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
    
}