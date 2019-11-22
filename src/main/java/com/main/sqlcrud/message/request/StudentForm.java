package com.main.sqlcrud.message.request;

import java.util.Date;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;


public class StudentForm {

    @NotNull
    private Long admissionNumber;

    @NotBlank
    @Size(min=3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min=3, max = 50)
    private String lastName;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
    private Date bday;

    @NotBlank
    private String address;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "UTC")
    private Date enrolledDate;

    @NotNull
    private Integer currentClassId;

    @NotBlank
    private String status;

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

	public Integer getCurrentClassId() {
		return currentClassId;
	}

	public void setCurrentClassId(Integer currentClassId) {
		this.currentClassId = currentClassId;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}