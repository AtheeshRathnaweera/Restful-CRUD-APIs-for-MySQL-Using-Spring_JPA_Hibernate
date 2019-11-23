package com.main.sqlcrud.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.main.sqlcrud.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Transactional@Repository
public interface UserRepository extends JpaRepository<User, String>{

     
     User findByUserId(String userId);
     List<User> findByUserRole(String role);
     
     Boolean existsByUserId(String userId);
 
     @Query("SELECT COUNT(u) FROM User u Where u.userRole= 'student'")
     ArrayList<Long> getStudentUsersCount();

     @Query("SELECT COUNT(u) FROM User u Where u.userRole= 'teacher'")
     ArrayList<Long> getTeacherUsersCount();



 
     // "SELECT COUNT(u) FROM User u Where u.userRole= ?#{[0]}"

     
    
}