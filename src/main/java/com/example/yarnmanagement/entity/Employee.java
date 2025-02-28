package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends UserYarnApp {

    public Employee(String firstName, String lastName, String email, String username, String password) {
        super(firstName, lastName, email, username, password);
        this.setRole(Role.Employee);
    }

    public Employee() {
    }


}
