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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="teacher_history")
public class TeacherHistory{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String nic;

    @NotNull
    private int year;

    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinTable(name = "teacher_class_history", 
    	joinColumns = @JoinColumn(name = "history_id", referencedColumnName="id"), 
    	inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName="id"))
    private SClass classId;

    public TeacherHistory() {
    }

    public TeacherHistory(@NotBlank String nic, @NotNull int year, @NotNull SClass classId) {
        this.nic = nic;
        this.year = year;
        this.classId = classId;
    }
    
    public TeacherHistory(Integer id, @NotBlank String nic, @NotNull int year, @NotNull SClass classId) {
        this.id = id;
        this.nic = nic;
        this.year = year;
        this.classId = classId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

  

    @Override
    public String toString() {
        return "TeacherHistory [classId=" + classId + ", id=" + id + ", nic=" + nic + ", year=" + year + "]";
    }

    

    


}