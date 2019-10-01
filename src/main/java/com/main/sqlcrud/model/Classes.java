package com.main.sqlcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="classes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "className"
    })
})
public class Classes{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @NaturalId
    @NotBlank
    @Size(min=1, max = 10)
    private String className;

    @NotNull
    private Long teacherId;

    public Classes(){}

    public Classes(String className,Long teacherId ){
        this.className = className;
        this.teacherId = teacherId;
    }

    public Long getId(){
        return id;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }

    public void setTeacherId(Long teacherId){
        this.teacherId = teacherId;
    }

    public Long getTeacherId(){
        return teacherId;
    }





}