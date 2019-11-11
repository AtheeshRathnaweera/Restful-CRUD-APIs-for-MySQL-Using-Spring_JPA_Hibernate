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

@Entity
@Table(name="student_history")
public class StudentHistory{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Long admissionNumber;

    @NotNull
    private Long year;

    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinTable(name = "student_class_history", 
    	joinColumns = @JoinColumn(name = "history_id", referencedColumnName="id"), 
    	inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName="id"))
    private SClass classId;

    public StudentHistory() {
    }

    public StudentHistory(Integer id, @NotNull Long admissionNumber, @NotNull Long year, @NotNull SClass classId) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.year = year;
        this.classId = classId;
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

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public SClass getClassId() {
        return classId;
    }

    public void setClassId(SClass classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "StudentHistory [admissionNumber=" + admissionNumber + ", classId=" + classId + ", id=" + id + ", year="
                + year + "]";
    }

    
    

}