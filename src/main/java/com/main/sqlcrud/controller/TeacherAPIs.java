package com.main.sqlcrud.controller;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.TeacherForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.repository.ClassRepository;
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
@RequestMapping("/api/studentMg/teacher")
public class TeacherAPIs {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassRepository classRepository;

    @PostMapping("/add") // Operators only
    public Teachers addingTeacher(@Valid @RequestBody TeacherForm newTeacher) {

        Teachers tempTeacher = new Teachers();

        if (!teacherRepository.existsById(newTeacher.getNIC())) {

            int currentClassId = newTeacher.getCurrentClassId();

            // Get the Class details
            SClass tempClass = null;

            if (currentClassId > 0) {
                tempClass = classRepository.findClassById(currentClassId);
            }

            Teachers tempT = new Teachers(newTeacher.getNIC(), newTeacher.getFirstName(), newTeacher.getLastName(),
                    newTeacher.getAddress(), newTeacher.getTelephoneNumber(), newTeacher.getStatus(),
                    newTeacher.getGender(), tempClass);

            tempTeacher = teacherRepository.save(tempT);

            return tempTeacher;

        } else {
            return tempTeacher;

        }

    }

    @PutMapping("/update") //
    public Teachers updateATeacher(@Valid @RequestBody TeacherForm teacherForm) {

        Teachers temp = teacherRepository.findByNic(teacherForm.getNIC());
        Teachers updateTeacher = null;
        SClass tempClass = null;

        if (temp != null) {
            temp.setFirstName(teacherForm.getFirstName());
            temp.setLastName(teacherForm.getLastName());
            temp.setAddress(teacherForm.getAddress());
            temp.setGender(teacherForm.getGender());
            temp.setStatus(teacherForm.getStatus());
            temp.setTelephoneNumber(teacherForm.getTelephoneNumber());

            if (teacherForm.getCurrentClassId() > 0) {
                tempClass = classRepository.findClassById(teacherForm.getCurrentClassId());
            }

            temp.setCurrentClass(tempClass);

            updateTeacher = teacherRepository.save(temp);

            return updateTeacher;

        } else {
            return updateTeacher;
        }
    }

    // get teacher details
    @GetMapping("/getTeacher/{teacherNic}") // get teacher details in the class
    public Teachers getTeacherDetails(@PathVariable(value = "teacherNic") String teacherNic) {

        return teacherRepository.findByNic(teacherNic);

    }

}