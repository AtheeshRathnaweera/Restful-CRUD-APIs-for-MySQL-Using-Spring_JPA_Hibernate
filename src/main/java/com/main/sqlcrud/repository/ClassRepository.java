package com.main.sqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.main.sqlcrud.model.SClass;

@Repository
public interface ClassRepository extends JpaRepository<SClass, Integer> {

	Boolean existsById(int id);
	SClass findClassById(int id);
	List<SClass> findByGrade(Integer grade);

	@Query("SELECT c.id FROM SClass c Where c.grade= ?#{[0]} AND c.name= ?#{[1]}")
	ArrayList<?> getClassIdByGradeAndName(int grade, String name);
}