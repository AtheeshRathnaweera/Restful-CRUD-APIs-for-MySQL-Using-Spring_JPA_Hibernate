package com.main.sqlcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User{

    
    @Id
    @Column(insertable = true,unique = true,nullable = false)
    private String userId; 
    
    @NotBlank
    private String password;

    private String userRole;

    public User() {
    }

    
    public User(String userId, @NotBlank String password, String userRole) {
        this.userId = userId;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    @Override
    public String toString() {
        return "User [password=" + password + ", userId=" + userId + ", userRole=" + userRole
                + "]";
    }

   

}