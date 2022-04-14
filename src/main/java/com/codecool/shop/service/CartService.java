package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;

public class CartService {
    private CartDao cartDao;
    private static CartService instance = null;

    private CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public static CartService createInitialInstance(CartDao cartDao){
        instance = new CartService(cartDao);
        return instance;
    }

    public static CartService getInstance(){
        return instance;
    }

    public List<CartProduct> getAll(){
        return cartDao.getAll();
    }

    public void add(Product product, int amount){
        cartDao.add(product, amount);
    }

    public CartProduct find(int productId){
        return cartDao.find(productId);
    }

    public void edit(int amount, int productId){
        cartDao.edit(amount, productId);
    }

}
