package com.main.sqlcrud.repository;

import com.main.sqlcrud.model.SClass;
import com.main.sqlcrud.model.Teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teachers, String>{
    Boolean existsByNic(String nic);

    Teachers findByNic(String nic);

    Teachers findByCurrentClass(SClass currentClass);

    @Query("SELECT t FROM Teachers t Where t.currentClass.id= ?#{[0]}")
	Teachers getTeacherByClass(int classId);

    
}