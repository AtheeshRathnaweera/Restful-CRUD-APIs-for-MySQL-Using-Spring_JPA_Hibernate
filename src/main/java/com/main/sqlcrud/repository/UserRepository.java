package com.main.sqlcrud.repository;

import com.main.sqlcrud.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
     User findByUserId(String userId);
     
     Boolean existsByUserId(String userId);
    
}