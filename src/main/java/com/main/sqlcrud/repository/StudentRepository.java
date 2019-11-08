package com.main.sqlcrud.repository;


import com.main.sqlcrud.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Boolean existsByAdmissionNumber(Long admissionNumber);

    Student findByAdmissionNumber(Long admissionNumber);

    
    
}

