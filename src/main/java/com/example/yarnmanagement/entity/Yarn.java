package com.example.yarnmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Table(name="Yarn")
public class Yarn {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Enumerated
    private Material material;

    @NotBlank
    private String color;

    @PositiveOrZero
    private double weight;
    @Enumerated
    private WeightType weightType;

    @PositiveOrZero
    private double price;

    @PositiveOrZero
    private int length;

    @OneToMany(mappedBy = "yarn", cascade = CascadeType.ALL)
    private List<OrderYarnDetail> orderYarnDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    public Yarn(Material material, String color, double weight, WeightType weightType, double price, int length) {
        this.material = material;
        this.color = color;
        this.weight = weight;
        this.weightType = weightType;
        this.price = price;
        this.length = length;
    }

    public Yarn() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public WeightType getWeightType() {
        return weightType;
    }

    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<OrderYarnDetail> getOrderYarnDetails() {
        return orderYarnDetails;
    }

    public void setOrderYarnDetails(List<OrderYarnDetail> orderYarnDetails) {
        this.orderYarnDetails = orderYarnDetails;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Yarn{" +
                "id=" + id +
                ", material=" + material +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", weightType=" + weightType +
                ", price=" + price +
                ", length=" + length +
                '}';
    }
}
