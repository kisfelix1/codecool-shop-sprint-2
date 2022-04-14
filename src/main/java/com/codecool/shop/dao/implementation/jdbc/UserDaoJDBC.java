package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC implements UserDao {

    private DataSource dataSource;
    private static UserDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private UserDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDaoJDBC getInitialInstance(DataSource dataSource) {
        instance = new UserDaoJDBC(dataSource);

        return instance;
    }

    @Override
    public void add(User user) {
        try(Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO eshop_user (full_name, email, password) VALUES(?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User find(String email, String password) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, full_name, email, password FROM eshop_user WHERE email = ? AND password = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            User user = new User(rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));
            user.setId(rs.getInt(1));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String find(String email) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT full_name FROM eshop_user WHERE email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                System.out.println("ERROR");
                return null;
            }
            System.out.println(rs.getString(1));
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
