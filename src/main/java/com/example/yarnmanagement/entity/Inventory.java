package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToOne (mappedBy = "inventory", cascade = CascadeType.ALL) //id cua Inventory se xuat hien o yarn la 1 foreign key
    private Yarn yarn;

    @Positive
    private int quantity;

    public Inventory(int quantity) {
        this.quantity = quantity;
    }

    public Inventory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Yarn getYarn() {
        return yarn;
    }

    public void setYarn(Yarn yarn) {
        this.yarn = yarn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
