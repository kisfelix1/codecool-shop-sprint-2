package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {
    Supplier find(int id);

    List<Supplier> getAll();
}
