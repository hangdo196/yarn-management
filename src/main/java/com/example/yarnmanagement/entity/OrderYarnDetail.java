package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderYarnDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)

    private OrderYarn order;

    @ManyToOne(cascade = CascadeType.ALL)
    private Yarn yarn;

    @PositiveOrZero
    private int quantity;
    public OrderYarnDetail(int quantity) {
        this.quantity = quantity;
    }

    public OrderYarnDetail() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderYarn getOrder() {
        return order;
    }

    public void setOrder(OrderYarn order) {
        this.order = order;
    }

    public Yarn getYarn() {
        return yarn;
    }

    public void setYarn(Yarn yarn) {
        this.yarn = yarn;
    }

    @Override
    public String toString() {
        return "OrderYarnDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}

