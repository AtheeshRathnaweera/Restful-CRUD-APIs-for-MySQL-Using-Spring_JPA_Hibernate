package com.main.sqlcrud.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.NewClassForm;
import com.main.sqlcrud.message.request.TeacherForm;
import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.TeacherHistory;
import com.main.sqlcrud.model.Teachers;
import com.main.sqlcrud.model.User;
import com.main.sqlcrud.repository.ClassRepository;
import com.main.sqlcrud.repository.TeacherHistoryRepository;
import com.main.sqlcrud.repository.TeacherRepository;
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
@RequestMapping("/api/studentMg/teacher")
public class TeacherAPIs {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherHistoryRepository teacherHistoryRepository;

    @PostMapping("/add") // Operators only
    public Teachers addingTeacher(@Valid @RequestBody TeacherForm newTeacher) {

        Teachers tempTeacher = new Teachers();
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (!teacherRepository.existsById(newTeacher.getNIC())) {

            int currentClassId = newTeacher.getCurrentClassId();
            TeacherHistory teacherHistory = teacherHistoryRepository.findByNic(newTeacher.getNIC());

            // Get the Class details
            SClass tempClass = null;

            if (currentClassId > 0) {
                tempClass = classRepository.findClassById(currentClassId);
            }else{
                List<SClass> ttclass = classRepository.findByGrade(0);
                tempClass = ttclass.get(0);
            }

            Teachers tempT = new Teachers(newTeacher.getNIC(), newTeacher.getFirstName(), newTeacher.getLastName(),
                    newTeacher.getAddress(), newTeacher.getTelephoneNumber(), newTeacher.getStatus(),
                    newTeacher.getGender(), tempClass);

            tempTeacher = teacherRepository.save(tempT);

            if(teacherHistory == null){
                teacherHistoryRepository.save(new TeacherHistory(newTeacher.getNIC(), year, tempClass));

            }else{
                if(year != teacherHistory.getYear()){
                    teacherHistoryRepository.save(new TeacherHistory(newTeacher.getNIC(), year, tempClass));
                }
            }

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
            int year = Calendar.getInstance().get(Calendar.YEAR);
            TeacherHistory teacherHistory = teacherHistoryRepository.findByNic(teacherForm.getNIC());

            temp.setFirstName(teacherForm.getFirstName());
            temp.setLastName(teacherForm.getLastName());
            temp.setAddress(teacherForm.getAddress());
            temp.setGender(teacherForm.getGender());
            temp.setStatus(teacherForm.getStatus());
            temp.setTelephoneNumber(teacherForm.getTelephoneNumber());

            if (teacherForm.getCurrentClassId() > 0) {
                tempClass = classRepository.findClassById(teacherForm.getCurrentClassId());
            }else{
                List<SClass> classl = classRepository.findByGrade(0);
                tempClass = classl.get(0);
            }

            temp.setCurrentClass(tempClass);

            updateTeacher = teacherRepository.save(temp);

            if(teacherHistory == null){
                teacherHistoryRepository.save(new TeacherHistory(updateTeacher.getNic(), year, tempClass));

            }else{
                if(year != teacherHistory.getYear()){
                    teacherHistoryRepository.save(new TeacherHistory(updateTeacher.getNic(), year, tempClass));
                }
            }

            return updateTeacher;

        } else {
            return updateTeacher;
        }
    }


    //REMOVING AND REASSIGNING//////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/remove/{nic}/{status}") // retired and blocked
    public Boolean removeATeacher(@PathVariable(value = "nic") String nic, @PathVariable(value = "status") String status ){
        Teachers existTeacher = teacherRepository.findByNic(nic);
        User existsUser = userRepository.findByUserId(nic);

        if(existTeacher != null && status != "" ){
            existTeacher.setStatus(status);
            teacherRepository.save(existTeacher);

            if(existsUser != null){ //Delete the user
                userRepository.deleteById(nic);
            }
            
            return true;
        }else{
            return false;
        }

    }

    @PutMapping("/reassign/{nic}")
    public Teachers reassignTeacher(@PathVariable(value="nic") String nic){
        Teachers existTeacher = teacherRepository.findByNic(nic);
        Teachers updatedUser = null;

        if(!existTeacher.equals(null)){
            existTeacher.setStatus("active");
            updatedUser = teacherRepository.save(existTeacher);
        }

        return updatedUser;
    }

    //REMOVING AND REASSIGNING//////////////////////////////////////////////////////////////////////////////

    //Get All teachers
    @GetMapping("/getall") // get teacher details by nic
    public List<Teachers> getAllTeachers() {

        return teacherRepository.findAll();

    }

    //GET ALL TEACHERS COUNT
    @GetMapping("/getallcount")
    public Long getAllTeachersCount() {

        return teacherRepository.count();

    }

    // get teacher details
    @GetMapping("/get/{teacherNic}") // get teacher details by nic
    public Teachers getTeacherDetails(@PathVariable(value = "teacherNic") String teacherNic) {

        return teacherRepository.findByNic(teacherNic);

    }

    // GET TEACHER BY STATUS TYPE//////////////////////////////////////////////////////////////////////////
    @GetMapping("/getbystatus/{status}") // get teacher details by nic
    public List<Teachers> getByStatus(@PathVariable(value = "status") String status) {

        return teacherRepository.findByStatus(status);

    }

    // GET TEACHER BY CLASS //////////////////////////////////////////////////////////////////////////////
    @GetMapping("/getbyclass/{classid}") 
    public List<Teachers> getByClass(@PathVariable(value = "classid") String classid) {

        return teacherRepository.findByCurrentClass(new SClass(Integer.parseInt(classid)));

    }

    //ASSIGN A Class

    

}