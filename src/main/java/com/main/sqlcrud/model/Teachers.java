package com.main.sqlcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="teachers", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "NIC"
    })
})
public class Teachers{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

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
    @NaturalId
    @Size(min=9, max = 11)
    private Long NIC;

    @NotNull
    @Size(min=9, max = 10)
    private Long telephoneNumber;

    public Teachers(){}

    public Teachers(String firstName,String lastName,String address,Long NIC,Long telephoneNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.NIC = NIC;
        this.telephoneNumber = telephoneNumber;

    }

    public Long getId(){
        return id;
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

    public String getAdress(){
        return address;
    }

    public void setNIC(Long NIC){
        this.NIC = NIC;
    }

    public Long getNIC(){
        return NIC;
    }

    public void setTelephoneNumber(Long telephoneNumber){
        this.telephoneNumber = telephoneNumber;
    }

    public Long getTelephoneNumber(){
        return telephoneNumber;
    }




}