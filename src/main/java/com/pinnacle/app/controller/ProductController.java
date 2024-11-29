package com.pinnacle.app.controller;

import com.pinnacle.app.model.Category;
import com.pinnacle.app.model.Product;
import com.pinnacle.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllCategoriesByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2",name ="size") Integer size) {
       // return service.getProductsByPagination(page, size);
        return new ResponseEntity<List<Product>>(service.getProductsByPagination(page, size), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        System.out.println(product);
        return new ResponseEntity<Product>(service.registerProduct(product),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>  updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return new ResponseEntity<String>(service.updateProduct(id,product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {

        return  new ResponseEntity<String>(service.deleteProductById(id),HttpStatus.OK);
    }

}
