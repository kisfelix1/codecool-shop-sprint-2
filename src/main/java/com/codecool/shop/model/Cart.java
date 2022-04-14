package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart extends BaseModel {

    private List<Product> items;


    public Cart(String name, String description) {
        super(name);
        this.items = new ArrayList<>();
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public void addItem(Product product) {
        this.items.add(product);
    }

    @Override
    public String toString() {
        return "";
    }
}
