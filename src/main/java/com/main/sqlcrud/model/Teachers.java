package com.main.sqlcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="teachers")
public class Teachers{
    
    @Id
    @Column(insertable = true, updatable = true,unique = true,nullable = false)
    @Size(min=9, max = 11)
    private String nic;

    @NotBlank
    @Size(min=3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min=3, max = 50)
    private String lastName;

    @NotBlank
    @Size(min=3, max = 50)
    private String address;

    @NotNull
    private Long telephoneNumber;

    public Teachers(){}

    public Teachers(String nic,String firstName,String lastName,String address,Long telephoneNumber){

        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;

    }

    public void setNIC(String nic){
        this.nic = nic;
    }

    public String getNIC(){
        return nic;
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