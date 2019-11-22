package com.main.sqlcrud.repository;

import java.util.ArrayList;
import java.util.List;

import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String>{
    Boolean existsByNic(String nic);
    Teachers findByNic(String nic);
    ArrayList<Teachers> findByStatus(String status);
    List<Teachers> findByCurrentClass(SClass currentClass);

    @Query("SELECT t FROM Teachers t Where t.currentClass.id= ?#{[0]}")
	List<Teachers> getTeacherByClass(int classId);

    
}