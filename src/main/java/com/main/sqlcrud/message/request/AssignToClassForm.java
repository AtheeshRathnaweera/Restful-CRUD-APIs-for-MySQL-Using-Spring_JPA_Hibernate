package com.main.sqlcrud.message.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssignToClassForm{

    @NotNull
    private String classId;

    @NotEmpty
    private String[] studentsAds;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String[] getStudentsAds() {
        return studentsAds;
    }

    public void setStudentsAds(String[] studentsAds) {
        this.studentsAds = studentsAds;
    }

    

    
    

}