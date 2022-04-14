package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService{
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;
    private static ProductService instance = null;

    private ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public static ProductService getInstance() {
        if (instance == null) {
            throw new Error();      //  database is not yet created, call getInitialInstance()
        }
        return instance;
    }

    public static void createInitialInstance(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        instance = new ProductService(productDao, productCategoryDao, supplierDao);
    }

    public Product getProductById(int id){
        return productDao.find(id);
    }


    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        if(categoryId == 0){
            return productDao.getAll();
        }
        else{
            var category = productCategoryDao.find(categoryId);
            return productDao.getBy(category);
        }
    }


    public List<ProductCategory> getAllCategories(){
        return productCategoryDao.getAll();
    }


    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<Product> getFilteredProducts(String categoryId, String supplierId) {
        return productDao
                .getAll()
                .stream()
                .filter(product -> (product
                        .getProductCategory().getId()==Integer.parseInt(categoryId)||categoryId.equals("0"))&&
                        (product.getSupplier().getId()==Integer.parseInt(supplierId)||supplierId.equals("0")))
                .collect(Collectors.toList());
    }
}
