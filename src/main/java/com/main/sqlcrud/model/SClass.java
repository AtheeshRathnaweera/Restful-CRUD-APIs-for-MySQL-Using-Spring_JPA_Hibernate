package com.main.sqlcrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="class")
public class SClass{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer grade;

    @NotBlank
    private String name;

    public SClass() {
    }

    public SClass(@NotNull Integer grade, @NotBlank String name) {
        this.grade = grade;
        this.name = name;
    }

    public SClass(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SClass [grade=" + grade + ", id=" + id + ", name=" + name + "]";
    }

 

   

}