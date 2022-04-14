package com.codecool.shop.dao.implementation.jdbc;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private DataSource dataSource;
    private static ProductCategoryDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductCategoryDaoJDBC getInitialInstance(DataSource dataSource) {
        instance = new ProductCategoryDaoJDBC(dataSource);
        return instance;
    }


    @Override
    public ProductCategory find(int id) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, category_name, department FROM product_category WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            ProductCategory productCategory = new ProductCategory(rs.getString(2), rs.getString(3));
            productCategory.setId(rs.getInt(1));
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategories = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id FROM product_category";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Integer id : ids) {
            productCategories.add(find(id));
        }
        return productCategories;
    }
}
