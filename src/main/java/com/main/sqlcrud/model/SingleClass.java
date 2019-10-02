package com.main.sqlcrud.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="class")
public class SingleClass{
    
    @Id
    @Column(name="className", insertable=true, updatable=true, unique=true, nullable=false)
    @Size(min=1, max = 4)
    private String className;

    @NotNull
    private Long teacherId;

    @OneToMany(fetch = FetchType.LAZY) 
    @JoinTable(name = "student_class", 
    	joinColumns = @JoinColumn(name = "class_id", referencedColumnName="className"), 
    	inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName="admissionNum"))
    private Set<Student> students = new HashSet<>();

    public SingleClass(){}

    public SingleClass(String className,Long teacherId){
        this.className = className;
        this.teacherId = teacherId;
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

    public void setStudents(Set<Student> students){
        this.students = students;
    }

    public Set<Student> getStudents(){
        return students;
    }



}