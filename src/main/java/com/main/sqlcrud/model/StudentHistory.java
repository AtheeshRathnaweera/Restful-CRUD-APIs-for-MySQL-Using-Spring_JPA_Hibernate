package com.main.sqlcrud.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student_history")
public class StudentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Long admissionNumber;

    @NotNull
    private int year;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinTable(name = "student_class_history", joinColumns = @JoinColumn(name = "history_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"))
    private SClass classId;

    private String subjects;

    private String firstTermResults;

    private String secondTermResults;

    private String thirdTermResults;

    public StudentHistory() {
    }

    public StudentHistory(@NotNull Long admissionNumber, @NotNull int year, @NotNull SClass classId) {
        this.admissionNumber = admissionNumber;
        this.year = year;
        this.classId = classId;
    }

    public StudentHistory(Integer id, @NotNull Long admissionNumber, @NotNull int year, @NotNull SClass classId,
            String subjects, String firstTermResults, String secondTermResults, String thirdTermResults) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.year = year;
        this.classId = classId;
        this.subjects = subjects;
        this.firstTermResults = firstTermResults;
        this.secondTermResults = secondTermResults;
        this.thirdTermResults = thirdTermResults;
    }

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

    public SClass getClassId() {
        return classId;
    }

    public void setClassId(SClass classId) {
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

    @Override
    public String toString() {
        return "StudentHistory [admissionNumber=" + admissionNumber + ", classId=" + classId + ", firstTermResults="
                + firstTermResults + ", id=" + id + ", secondTermResults=" + secondTermResults + ", subjects="
                + subjects + ", thirdTermResults=" + thirdTermResults + ", year=" + year + "]";
    }

  

}