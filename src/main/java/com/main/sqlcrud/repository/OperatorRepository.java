package com.main.sqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.main.sqlcrud.model.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, String> {

    Boolean existsByNic(String nic);
    Operator findByNic(String nic);


}