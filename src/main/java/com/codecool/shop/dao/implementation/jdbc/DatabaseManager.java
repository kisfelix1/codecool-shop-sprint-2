package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.DbConfig;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DatabaseManager {

    public DataSource getDataSource() throws SQLException, FileNotFoundException {
        try {
            DataSource dataSource = connect();
            return dataSource;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database!");
            throw e;
        } catch (FileNotFoundException ex){
            System.out.println("Couldn't find db_config file!");
            throw ex;
        }
    }

    public static DataSource connect() throws SQLException, FileNotFoundException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = DbConfig.getInstance().getDbName();
        String user = DbConfig.getInstance().getDbUserName();
        String password = DbConfig.getInstance().getDbbPassword();

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
