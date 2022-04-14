package com.codecool.shop.dao;

import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {

    void add(Product product, int amount);
    void edit(int value, int id);
    CartProduct find(int id);

    List<CartProduct> getAll();
}
