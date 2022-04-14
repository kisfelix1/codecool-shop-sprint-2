package com.codecool.shop.dao.implementation.jdbc;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJDBC implements CartDao {
    private DataSource dataSource;
    private List<CartProduct> data = new ArrayList<>();
    private static CartDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private CartDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CartDaoJDBC getInitialInstance(DataSource dataSource) {
        instance = new CartDaoJDBC(dataSource);
        return instance;
    }

    @Override
    public void add(Product product, int amount) {
        data.add(new CartProduct(product, amount));
    }

    @Override
    public void edit(int amount, int id) {
        CartProduct cartProduct = find(id);
        if (cartProduct.getAmount() + amount < 1) {
            data.remove(cartProduct);
        } else {
            cartProduct.setAmount(cartProduct.getAmount() + amount);
        }
    }

    @Override
    public CartProduct find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    public BigDecimal getTotalCartPrice(){
        return data.stream()
                .map(e -> e.getDefaultPrice()
                        .multiply(BigDecimal.valueOf(e.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<CartProduct> getAll() {
        return data;
    }
}
