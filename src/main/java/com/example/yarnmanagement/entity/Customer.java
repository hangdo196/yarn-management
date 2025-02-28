package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer extends UserYarnApp {
    @NotBlank
    private String streetname;

    @Positive
    private int houseNumber;

    @NotBlank
    private String city;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderYarn> orders;

    public Customer(String firstName, String lastName, String email, String username, String password, String streetname, int houseNumber, String city) {
        super(firstName, lastName, email, username, password);
        this.setRole(Role.Customer);
        this.streetname = streetname;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public Customer() {
        super();
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<OrderYarn> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderYarn> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                super.toString() +
                ", streetname='" + streetname + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
