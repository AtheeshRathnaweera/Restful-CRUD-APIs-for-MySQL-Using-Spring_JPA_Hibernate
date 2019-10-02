package com.main.sqlcrud.message.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewClassForm {

    @NotBlank
    private String className;

    @NotNull
    private Long teacherId;

    private List<Long> students;//Store student s admission numbers

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

    public void setStudents(List<Long> students){
        this.students = students;
    }

    public List<Long> getStudents(){
        return students;
    }
}