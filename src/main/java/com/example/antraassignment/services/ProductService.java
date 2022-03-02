package com.example.antraassignment.services;

import com.example.antraassignment.dao.ProductDao;
import com.example.antraassignment.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public Product getProductById(Long id) {
        return productDao.getProducts().get(id);
    }

    public Map<Long, Product> getAllProduct() {
        return productDao.getProducts();
    }

    public void createNewProduct(String name, Integer price) {
        Long newId = Collections.max(productDao.getProducts().keySet()) + 1;
        productDao.getProducts().put(newId, new Product(name, price));
    }

    public Product updateProduct(Long id, String name, Integer price) {
        Product product = getProductById(id);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    public void deleteProduct(Long id) {
        productDao.getProducts().remove(getProductById(id));
    }
}
