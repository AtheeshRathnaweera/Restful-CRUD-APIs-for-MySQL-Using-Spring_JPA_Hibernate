package com.main.sqlcrud.repository;

import java.util.ArrayList;
import java.util.List;

import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Student;
import com.main.sqlcrud.model.TeacherHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherHistoryRepository extends JpaRepository<TeacherHistory, String>{
    TeacherHistory findByNic(String nic);


}