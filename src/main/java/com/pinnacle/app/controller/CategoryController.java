package com.pinnacle.app.controller;

import com.pinnacle.app.model.Category;
import com.pinnacle.app.model.Product;
import com.pinnacle.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    @Autowired
    private CategoryService service;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategoriesByPage(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "3",name ="size") Integer size) {
        return  new ResponseEntity<List<Category>>(service.getAllCategoriesByPagination(page, size), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
         return new  ResponseEntity<Category> (  service.createCategory(category),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id)
    {
        Category categories = (service.getCategoryById(id));
        System.out.println(categories);
        return new ResponseEntity<Category>(categories,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String>  updateCategory(@PathVariable Integer id, @RequestBody Category category)
    {
        return new ResponseEntity<String>(service.updateCategory(id, category),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<String>(service.deleteCategoryById(id),HttpStatus.OK);
    }
}
