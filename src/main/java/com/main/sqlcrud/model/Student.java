package com.main.sqlcrud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="student")
public class Student{

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="admissionNum", insertable=true, updatable=true, unique=true, nullable=false)
    private Long admissionNumber;

    @NotBlank
    @Size(min=3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min=3, max = 50)
    private String lastName;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
    private Date bday;

    @NotBlank
    private String address;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
    private Date enrolledDate;

    public Student(){}

    public Student(Long admissionNumber,String firstName,String lastName,Date bday,String address,Date enrolledDate){

        this.admissionNumber = admissionNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bday = bday;
        this.address = address;
        this.enrolledDate = enrolledDate;

    }

    public Student(Long admissionNumber){
        this.admissionNumber = admissionNumber;
    }

    public void setAdmissionNumber(Long admissionNumber){
        this.admissionNumber = admissionNumber;
    }

    public Long getAdmissionNumber(){
        return admissionNumber;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setBday(Date bday){
        this.bday = bday;
    }

    public Date getBday(){
        return bday;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setEnrolledDate(Date enrolledDate){
        this.enrolledDate = enrolledDate;
    }

    public Date getEnrolledDate(){
        return enrolledDate;
    }

}