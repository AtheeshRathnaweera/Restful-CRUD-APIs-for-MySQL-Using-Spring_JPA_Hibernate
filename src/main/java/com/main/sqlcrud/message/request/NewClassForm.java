package com.main.sqlcrud.message.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewClassForm {

    @NotNull
    private Integer grade;

    @NotBlank
    private String name;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}