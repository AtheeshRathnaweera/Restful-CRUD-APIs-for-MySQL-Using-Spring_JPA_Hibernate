package com.main.sqlcrud.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@Entity
//@Table(name="class")
public class SingleClassNo{
    
    @Id
    @Column(name="className", insertable=true, updatable=true, unique=true, nullable=false)
    @Size(min=1, max = 4)
    private String className;

    @NotNull
    @Size(min=9, max = 11)
    private String teacherNic;

    @OneToMany(fetch = FetchType.LAZY) 
    @JoinTable(name = "student_class", 
    	joinColumns = @JoinColumn(name = "class_id", referencedColumnName="className"), 
    	inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName="admissionNum"))
    private Set<Student> students = new HashSet<>();

    public SingleClassNo(){}

    public SingleClassNo(String className,String teacherNic){
        this.className = className;
        this.teacherNic = teacherNic;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }

    public void setTeacherNic(String teacherNic){
        this.teacherNic = teacherNic;
    }

    public String getTeacherNic(){
        return teacherNic;
    }

    public void setStudents(Set<Student> students){
        this.students = students;
    }

    public Set<Student> getStudents(){
        return students;
    }



}