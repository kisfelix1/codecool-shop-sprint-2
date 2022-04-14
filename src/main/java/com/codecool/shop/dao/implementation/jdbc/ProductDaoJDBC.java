package com.codecool.shop.dao.implementation.jdbc;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;

    private final DataSource dataSource;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJDBC(DataSource dataSource, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.dataSource = dataSource;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public static ProductDaoJDBC getInitialInstance(DataSource dataSource, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        instance = new ProductDaoJDBC(dataSource, productCategoryDao, supplierDao);
        return instance;
    }



    @Override
    public Product find(int id) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, product_name, price, currency, description, image_path, supplier_id, category_id FROM product WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            Product product = new Product(rs.getString(2),
                    rs.getBigDecimal(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    productCategoryDao.find(rs.getInt(8)),
                    supplierDao.find(rs.getInt(7)));
            product.setId(rs.getInt(1));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id FROM product";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Integer id : ids) {
            products.add(find(id));
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return getAll().stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return getAll().stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
