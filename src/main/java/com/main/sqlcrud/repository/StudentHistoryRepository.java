package com.main.sqlcrud.repository;

import java.util.ArrayList;
import java.util.List;


import com.main.sqlcrud.model.StudentHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentHistoryRepository extends JpaRepository<StudentHistory, Long>{

    List<StudentHistory> findByAdmissionNumber(Long admissionNumber);

    @Query("SELECT c FROM StudentHistory c Where c.id= ?#{[0]}")
    StudentHistory getStudentById(int id);

    @Query("SELECT c FROM StudentHistory c Where c.year= ?#{[0]} AND c.admissionNumber= ?#{[1]}")
	StudentHistory getHistoryByYearAndAdmissionNum(int year, Long adm);


}