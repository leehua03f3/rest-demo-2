package com.example.antraassignment.controllers;

import com.example.antraassignment.pojos.Product;
import com.example.antraassignment.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.Map;

@RestController
@Validated
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping("/getproductbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        if (!checkId(id)) {
            return ResponseEntity.notFound().build();
        }
        logger.info("Successfully get product by id = " + id);
        return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/getallproduct")
    public ResponseEntity<Map<Long, Product>> getAllProduct() {
        Map<Long, Product> allProducts = productService.getAllProduct();
        logger.info("Successfully get " + allProducts.size() + " products");
        return new ResponseEntity<Map<Long, Product>>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/createnewproduct")
    public ResponseEntity<Product> createNewProduct(@RequestParam @NotNull @Size(max=500) String name,
                                                    @RequestParam @NotNull @Min(10) @Max(2000) Integer price) {
        logger.info("Successfully create new product name " + name);
        productService.createNewProduct(name, price);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateproduct")
    public ResponseEntity<Product> updateProduct(@RequestParam @NotNull Long id,
                                                 @RequestParam @NotNull @Size(max = 500) String name,
                                                 @RequestParam @NotNull @Min(1) @Max(2000) Integer price) {
        if (!checkId(id)) {
            return ResponseEntity.notFound().build();
        }
        Product updatedProduct = productService.updateProduct(id, name, price);
        logger.info("Successfully update product id " + id);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteproduct")
    public ResponseEntity deleteProduct(@RequestParam @NotNull Long id) {
        if (!checkId(id)) {
            return ResponseEntity.notFound().build();
        }
        logger.info("Successfully delete product id " + id);
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }

    public boolean checkId(Long id) {
        if (productService.getProductById(id) == null) {
            logger.debug("Product is not found");
            return false;
        } else {
            return true;
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleException (MethodArgumentNotValidException ex) {
        logger.debug(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException (Exception ex) {
        logger.debug(ex.getMessage());
    }
}
