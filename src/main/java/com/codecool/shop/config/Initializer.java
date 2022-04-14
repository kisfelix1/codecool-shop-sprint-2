package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDaoMem productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDaoMem productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier valve = new Supplier("Valve", "Valve is a video game developer and publisher company");
        supplierDataStore.add(valve);
        Supplier opensea = new Supplier("OpenSea", "A peer-to-peer marketplace for NFTs");
        supplierDataStore.add(opensea);
        Supplier staub = new Supplier("Staub", "A quality knife producer.");
        supplierDataStore.add(staub);

        //setting up a new product category
        ProductCategory knife = new ProductCategory("Knife", "Cuisinart");
        productCategoryDataStore.add(knife);
        ProductCategory gun = new ProductCategory("Gun", "Guns");
        productCategoryDataStore.add(gun);
        ProductCategory nft = new ProductCategory("NFT", "Digital art");
        productCategoryDataStore.add(nft);

        //setting up products and printing it
        productDataStore.add(new Product("M4A4 | Howl", new BigDecimal("2000"), "USD", "CS:GO digital skin. Get your hand on a rare peace of art now!", "/static/img/product_5.jpg" , knife, valve));
        productDataStore.add(new Product("M4A4 | Poseidon", new BigDecimal("549.9"), "USD", "CS:GO digital skin. A mostly rare skin with rare condition. Feel the power of the sea!", "/static/img/product_6.jpg", knife, valve));
//        productDataStore.add(new Product("AWP | Dragon lore", new BigDecimal("1556"), "USD", "CS:GO digital skin. A nice skin with nordic motives and a nice dragon art.", gun, valve));
//        productDataStore.add(new Product("M4A1 | Imminent Danger", new BigDecimal("239"), "USD", "CS:GO digital skin. Warn others as you take the lead on the battlefield.", gun, valve));
//        productDataStore.add(new Product("M9 Bayonet | Crimson Web", new BigDecimal("258"), "USD", "CS:GO digital skin. A knife with red color and a black web design on the middle.", knife, valve));
//        productDataStore.add(new Product("Classic Knife", new BigDecimal("214"), "USD", "CS:GO digital skin. Feel the old times with this peace of art!", knife, valve));
//        productDataStore.add(new Product("Kitchen Knife", new BigDecimal("64"), "USD", "A quality knife. Perfect for cutting meat and vegetables!", knife, staub));
//        productDataStore.add(new Product("Golden monkey art", new BigDecimal("2999"), "USD", "A digital art from a golden monkey", nft, opensea));
//        productDataStore.add(new Product("Intelligent gorilla art", new BigDecimal("699"), "USD", "A digital art from a very intelligent gorilla", nft, opensea));
//        productDataStore.add(new Product("Frankenstein's apple art", new BigDecimal("755"), "USD", "A digital art from an apple cosplaying Frankenstein's monster", nft, opensea));
//        productDataStore.add(new Product("Surprised strawberry art", new BigDecimal("465"), "USD", "A digital art from a surprised strawberry", nft, opensea));
//        productDataStore.add(new Product("Cool bomb art", new BigDecimal("643"), "USD", "A digital art from a bomb", nft, opensea));
//        productDataStore.add(new Product("Pirate monkey art", new BigDecimal("265"), "USD", "A digital art from a monkey with an eye patch", nft, opensea));
//        productDataStore.add(new Product("Surprised bomb art", new BigDecimal("245"), "USD", "A digital art from a surprised bomb", nft, opensea));
//        productDataStore.add(new Product("Toxic doge art", new BigDecimal("435"), "USD", "A digital art from a toxic dog", nft, opensea));
    }
}