package com.main.sqlcrud.repository;

import java.util.ArrayList;
import java.util.List;

import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Boolean existsByAdmissionNumber(Long admissionNumber);

    Student findByAdmissionNumber(Long admissionNumber);
    List<Student> findByStatus(String status);
    List<Student> findByCurrentClass(SClass currentClass);

    @Query("SELECT s FROM Student s Where s.currentClass.id= ?#{[0]}")
    ArrayList<Student> getStudentsInAClass(int classId);
    
    @Query("SELECT COUNT(*) FROM Student s Where s.currentClass.id= ?#{[0]}")
    Long getStudentsCountInAClass(int classId);
    
    @Query("SELECT s FROM Student s Where s.status!= 'past'")
    List<Student> findAllActiveStudents();


    
    
}

