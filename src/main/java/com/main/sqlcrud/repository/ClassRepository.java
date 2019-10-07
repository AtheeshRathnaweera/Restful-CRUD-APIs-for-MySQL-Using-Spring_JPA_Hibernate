package com.main.sqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.sqlcrud.model.SingleClass;

@Repository
public interface ClassRepository extends JpaRepository<SingleClass, Long> {

	SingleClass findByclassName(String className);

	SingleClass findByTeacherNic(String teacherNic);



}