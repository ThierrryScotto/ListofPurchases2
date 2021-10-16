package com.thierry.listofpurchases;

import androidx.annotation.NonNull;

import java.security.PublicKey;

public class Product {
    public int id;
    public String name, category;
    public String quantity;

    public Product(){

    }

    public Product(String name, String category, String quantity) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | " + quantity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
