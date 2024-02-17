package com.gcu.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Table("users")
public class RegistrationEntity {
    
    @NotNull
    @Column("USERNAME")
    String username;

    @NotNull
    @Column("PASSWORD")
    String password;

    public RegistrationEntity(){
        this.username = "";
        this.password = "";
    }

    public RegistrationEntity(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
