package com.main.sqlcrud.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeacherForm {

    @NotNull
    private String NIC;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;

    @NotNull
    private Long telephoneNumber;

    public void setNIC(String NIC){
        this.NIC = NIC;
    }

    public String getNIC(){
        return NIC;
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

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setTelephoneNumber(Long telephoneNumber){
        this.telephoneNumber = telephoneNumber;
    }

    public Long getTelephoneNumber(){
        return telephoneNumber;
    }
}



