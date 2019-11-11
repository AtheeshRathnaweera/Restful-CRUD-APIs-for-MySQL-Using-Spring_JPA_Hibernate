package com.main.sqlcrud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    @NotNull
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinTable(name = "student_class", 
    	joinColumns = @JoinColumn(name = "student_id", referencedColumnName="admissionNum"), 
    	inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName="id"))
    private SClass currentClass;

    public Student() {
    }

    public Student(Long admissionNumber, @NotBlank @Size(min = 3, max = 50) String firstName,
            @NotBlank @Size(min = 3, max = 50) String lastName, @NotNull Date bday, @NotBlank String address,
            @NotNull Date enrolledDate, @NotNull SClass currentClass) {
        this.admissionNumber = admissionNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bday = bday;
        this.address = address;
        this.enrolledDate = enrolledDate;
        this.currentClass = currentClass;
    }

    public Long getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(Long admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(Date enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public SClass getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(SClass currentClass) {
        this.currentClass = currentClass;
    }

    @Override
    public String toString() {
        return "Student [address=" + address + ", admissionNumber=" + admissionNumber + ", bday=" + bday
                + ", currentClass=" + currentClass + ", enrolledDate=" + enrolledDate + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }

    

    

}