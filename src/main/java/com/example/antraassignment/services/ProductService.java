package com.example.antraassignment.services;

import com.example.antraassignment.dao.ProductDao;
import com.example.antraassignment.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

public interface ProductService {
    Product getProductById(Long Id);
    Map<Long, Product> getAllProducts();
    Product createNewProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
