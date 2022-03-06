package com.example.antraassignment.globalexceptions;

import com.example.antraassignment.controllers.ProductController;
import com.example.antraassignment.exceptions.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException (Exception ex) {
        logger.warn(ex.getMessage());
        ErrorResponse er = new ErrorResponse();
        er.setCode(500);
        er.setMsg("Server is down");
        return er;
    }
}
