package com.main.sqlcrud.repository;

import java.util.List;

import com.main.sqlcrud.model.Teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String>{
     

    
}