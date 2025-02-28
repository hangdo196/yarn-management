package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Chi dung cho TH Inheritance - ke thua o Oberklasse va Unterklass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserYarnApp implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank(message = "Password is required")
    //@Size(min = 8)
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[+@$!%*?&.])[A-Za-z\\d+@$!%*?&.]{8,}$",
    //        message = "Password is required at least one upper case, one lower case, one number, one special character " +
    //                "and must be at least 8 characters long!")
    //(?=.*[a-z]) at least 1 lower case
    //(?=.*[A-Z]) at least 1 upper case
    //(?=.*\d) at least 1 number
    //(?=.*[@$!%*?&]) at least one special character
    //[A-Za-z\d@$!%*?&] only consist of these characters -> ^[]la chi chua nhung cai nay
    //{8,} at least 8 long
    private String password;


    @NotBlank
    @Column(unique = true)
    private String username;

    @Enumerated
    private Role role;


    public UserYarnApp(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UserYarnApp() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(this.role.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }


    public void promoteToAdmin() {
        this.role = Role.AdminManager;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserYarnApp{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
