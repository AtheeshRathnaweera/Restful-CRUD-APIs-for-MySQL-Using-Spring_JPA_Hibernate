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
@Table(name="teacher_history")
public class TeacherHistory{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Long nic;

    @NotNull
    private Long year;

    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinTable(name = "teacher_class_history", 
    	joinColumns = @JoinColumn(name = "history_id", referencedColumnName="id"), 
    	inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName="id"))
    private SClass classId;

    public TeacherHistory() {
    }

    public TeacherHistory(Integer id, @NotNull Long nic, @NotNull Long year, @NotNull SClass classId) {
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

    public Long getNic() {
        return nic;
    }

    public void setNic(Long nic) {
        this.nic = nic;
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
        return "TeacherHistory [classId=" + classId + ", id=" + id + ", nic=" + nic + ", year=" + year + "]";
    }

    


}