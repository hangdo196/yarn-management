package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalTime;
import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderYarn {

    @ManyToOne (cascade = CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) //order xuat hien trong Orderyarndetail la 1 foreign key
    private List<OrderYarnDetail> orderYarnDetails;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private LocalTime orderDate;

    private OrderStatus orderStatus;

    @PositiveOrZero
    private int totalAmount;

    @PositiveOrZero
    private double totalPrice;


    public OrderYarn() {
        this.orderDate = LocalTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderYarnDetail> getOrderYarnDetails() {
        return orderYarnDetails;
    }

    public void setOrderYarnDetails(List<OrderYarnDetail> orderYarnDetails) {
        this.orderYarnDetails = orderYarnDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", id=" + id +
                ", totalAmount=" + totalAmount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
