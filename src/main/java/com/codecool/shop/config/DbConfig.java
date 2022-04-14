package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.mem.CartDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserService;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DbConfig {
    private String dbType;
    private String dbName;
    private String dbUserName;
    private String dDbPassword;
    private static DbConfig instance = null;

    private DbConfig() throws FileNotFoundException {
        setupDbConfig();
    }

    public static DbConfig getInstance() throws FileNotFoundException {
        if (instance == null){
            instance = new DbConfig();
        }
        return instance;
    }

    public static void setupDbConnection() {
        try {
            createDbConnection();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasDbConfigChanged() throws FileNotFoundException {
        if (instance == null){
            return true;
        }
        String newType = getDbConfigData().toString().split(" ")[0];
        return !instance.dbType.equals(newType);

    }

    private static void createDbConnection() throws SQLException, FileNotFoundException {
        instance = new DbConfig();
        String dbType = instance.getDbType();
        ProductDao productDataStore;
        ProductCategoryDao productCategoryDataStore;
        SupplierDao supplierDataStore;
        CartDao cartDataStore;

        if (dbType.equals("jdbc")) {
            DatabaseManager databaseManager = new DatabaseManager();
            DataSource dataSource = databaseManager.getDataSource();
            productCategoryDataStore = ProductCategoryDaoJDBC.getInitialInstance(dataSource);
            supplierDataStore = SupplierDaoJDBC.getInitialInstance(dataSource);
            productDataStore = ProductDaoJDBC.getInitialInstance(dataSource, productCategoryDataStore, supplierDataStore);
            cartDataStore = CartDaoJDBC.getInitialInstance(dataSource);

            UserDaoJDBC userDao = UserDaoJDBC.getInitialInstance(dataSource);
            UserService.createInitialInstance(userDao);

        } else {
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
            cartDataStore = CartDaoMem.getInstance();
        }
        ProductService.createInitialInstance(productDataStore, productCategoryDataStore, supplierDataStore);
        CartService.createInitialInstance(cartDataStore);
    }

    public void setupDbConfig() throws FileNotFoundException {
        String[] data = getDbConfigData().toString().split(" ");
        dbType = data[0];
        dbName = data[1];
        dbUserName = data[2];
        dDbPassword = data[3];
    }

    public String getDbType() {
        return dbType;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbbPassword() {
        return dDbPassword;
    }

    private static StringBuilder getDbConfigData() throws FileNotFoundException {
        StringBuilder data;
        try {
            File myObj = new File("src/main/java/com/codecool/shop/config/db_config.csv");
            Scanner myReader = new Scanner(myObj);
            data = new StringBuilder();
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            throw e;
        }
        return data;
    }

    public static void switchDb() throws FileNotFoundException {
        String[] data = getDbConfigData().toString().split(" ");
        if (instance.dbType.equals("jdbc")){
            data[0] = "memory";
        } else {
            data[0] = "jdbc";
        }
        writeFile(String.join(" ", data));
    }

    private static void writeFile(String data){
        try {
            FileWriter myWriter = new FileWriter("src/main/java/com/codecool/shop/config/db_config.csv");
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
