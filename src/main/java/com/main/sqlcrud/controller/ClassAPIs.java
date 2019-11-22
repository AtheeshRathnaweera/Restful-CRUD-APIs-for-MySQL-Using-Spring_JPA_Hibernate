package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.NewClassForm;
import com.main.sqlcrud.message.request.StudentForm;
import com.main.sqlcrud.message.request.TeacherForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.StudentRepository;
import com.main.sqlcrud.repository.TeacherRepository;

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
@RequestMapping("/api/studentMg/class")
public class ClassAPIs {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/add") // Operators only
    public boolean addingClass(@Valid @RequestBody NewClassForm newClassRequest) {

        SClass tempClass = new SClass(newClassRequest.getGrade(), newClassRequest.getName());

        ArrayList<?> result = classRepository.getClassIdByGradeAndName(newClassRequest.getGrade(),
                newClassRequest.getName());

        if (result.isEmpty()) {
            System.out.println("class not exists");
            classRepository.save(tempClass);
            return true;

        } else {
            System.out.println("class exists.");
            return false;
        }

    }

    // Get all classes of a grade
    @GetMapping("/getClasses/{grade}")
    public List<SClass> gettingClasses(@PathVariable(value = "grade") int grade) {
        List<SClass> result = classRepository.findByGrade(grade);
        System.out.println("result : " + result.size());
        return result;
    }

    // Get students of a class
    @GetMapping("/getStudents/{classId}")
    public List<Student> gettingStudents(@PathVariable(value = "classId") int classId) {
        List<Student> result = studentRepository.getStudentsInAClass(classId);
        System.out.println("result size : " + result.size());
        return result;
    }

    // Get class teacher
    @GetMapping("/getTeacher/{classId}")
    public List<Teachers> getClassTeacher(@PathVariable(value = "classId") int classId) {
        List<Teachers> result = teacherRepository.getTeacherByClass(classId);
        return result;
    }

}