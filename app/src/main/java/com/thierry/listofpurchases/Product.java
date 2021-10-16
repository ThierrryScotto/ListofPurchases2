package com.thierry.listofpurchases;

import androidx.annotation.NonNull;

import java.security.PublicKey;

public class Product {
    public int id;
    public String name, supermarket;
    public String quantity;

    public Product(){

    }

    public Product(String name, String supermarket, String quantity) {
        this.name = name;
        this.supermarket = supermarket;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " | " + supermarket + " | " + quantity;
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

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String category) {
        this.supermarket = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
