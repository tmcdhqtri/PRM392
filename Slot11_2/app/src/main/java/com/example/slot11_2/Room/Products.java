package com.example.slot11_2.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Products implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;
    private double price;

    public Products(int id, String name, String description,double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}
}
