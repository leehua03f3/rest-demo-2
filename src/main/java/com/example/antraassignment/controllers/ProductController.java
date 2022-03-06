package com.example.antraassignment.controllers;

import com.example.antraassignment.exceptions.ErrorResponse;
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

import java.util.Map;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class); // AOP

    @Autowired
    ProductService productService;

    @GetMapping("/getproductbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (!checkId(id)) {
            return ResponseEntity.notFound().build();
        }
        logger.info("Successfully get product by id = {}", id);
        return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/getallproducts")
    public ResponseEntity<Map<Long, Product>> getAllProducts() {
        Map<Long, Product> allProducts = productService.getAllProducts();
        logger.info("Successfully get " + allProducts.size() + " products");
        return new ResponseEntity<Map<Long, Product>>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/createnewproduct")
    public ResponseEntity<Product> createNewProduct(@RequestBody @Validated Product product) {
        logger.info("Successfully create new product name {}", product.getName());
        productService.createNewProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateproduct/{id}")
    public ResponseEntity<Map <Long, Product>> updateProduct(@PathVariable Long id,
                                                             @RequestBody @Validated Product product) {
        if (!checkId(id)) {
            return ResponseEntity.notFound().build();
        }
        productService.updateProduct(id, product);
        logger.info("Successfully update product id {}", id);
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteproduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        if (!checkId(id)) {
            return ResponseEntity.notFound().build();
        }
        logger.info("Successfully delete product id {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }

    public boolean checkId(Long id) {
        if (productService.getProductById(id) == null) {
            logger.error("Product is not found");
            return false;
        } else {
            return true;
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // AOP
    public ErrorResponse handleException (MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        ErrorResponse er = new ErrorResponse();
        er.setMsg("Bad request");
        er.setCode(400);
        er.setData(ex);
        return er;
    }

}
