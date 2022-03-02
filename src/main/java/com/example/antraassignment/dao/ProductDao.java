package com.example.antraassignment.dao;

import com.example.antraassignment.pojos.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDao {
    Map<Long, Product> products = new HashMap();

    public ProductDao() {
        products.put(0L, new Product("Table", 150));
        products.put(1L, new Product("Chair", 50));
        products.put(2L, new Product("Bed", 200));
        products.put(3L, new Product( "Door", 450));
        products.put(4L, new Product( "Closet", 240));
        products.put(5L, new Product( "Desk", 120));
    }

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }
}
