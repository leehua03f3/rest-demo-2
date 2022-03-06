package com.example.antraassignment.services;

import com.example.antraassignment.dao.ProductDao;
import com.example.antraassignment.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Primary
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Long id) {
        return productDao.getProducts().get(id);
    }

    @Override
    public Map<Long, Product> getAllProducts() {
        return productDao.getProducts();
    }

    @Override
    public Product createNewProduct(Product product) {
        Long newId = Collections.max(productDao.getProducts().keySet()) + 1;
        productDao.getProducts().put(newId, product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        productDao.getProducts().put(id, product);
        return product;
    }

    public void deleteProduct(Long id) {
        productDao.getProducts().remove(id);
    }
}
