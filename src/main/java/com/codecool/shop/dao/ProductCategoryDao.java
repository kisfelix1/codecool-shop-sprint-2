package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    ProductCategory find(int id);

    List<ProductCategory> getAll();

}
