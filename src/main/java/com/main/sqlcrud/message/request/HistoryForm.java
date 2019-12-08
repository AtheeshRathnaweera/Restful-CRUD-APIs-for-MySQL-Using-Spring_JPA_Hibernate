package com.main.sqlcrud.message.request;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

public class HistoryForm{

    @NotNull
    private Integer id;

    private Long admissionNumber;
    private int year;
    private int classId;
    private String subjects;
    private String firstTermResults;
    private String secondTermResults;
    private String thirdTermResults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(Long admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getFirstTermResults() {
        return firstTermResults;
    }

    public void setFirstTermResults(String firstTermResults) {
        this.firstTermResults = firstTermResults;
    }

    public String getSecondTermResults() {
        return secondTermResults;
    }

    public void setSecondTermResults(String secondTermResults) {
        this.secondTermResults = secondTermResults;
    }

    public String getThirdTermResults() {
        return thirdTermResults;
    }

    public void setThirdTermResults(String thirdTermResults) {
        this.thirdTermResults = thirdTermResults;
    }

    
}