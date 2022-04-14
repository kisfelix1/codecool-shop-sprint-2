package com.codecool.shop.model;

public class CartProduct extends Product {

    private int amount;

    public CartProduct(Product product, int amount) {
        super(product.name, product.getDefaultPrice(), product.getDefaultCurrency().toString(), product.description,product.getImagePath(), product.getProductCategory(), product.getSupplier());
        this.amount = amount;
        this.id = product.getId();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
