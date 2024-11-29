package com.pinnacle.app.errorAdvice;

import com.pinnacle.app.exception.CategoryNotFoundException;
import com.pinnacle.app.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController
{
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleExceptionForCategory(CategoryNotFoundException e)
    {

        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleExceptionForProduct(ProductNotFoundException e)
    {

        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptionGlobally(Exception e)
    {

        return new ResponseEntity<String>("404 Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
