package com.pinnacle.app.service;

import com.pinnacle.app.dao.CategoryRepository;
import com.pinnacle.app.exception.CategoryNotFoundException;
import com.pinnacle.app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository repo;

    public Category createCategory(Category category)
    {
        return repo.save(category);
    }

    public Category getCategoryById(Integer id)
    {
        return repo.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    public String updateCategory(Integer id, Category updateCategory)
    {
        Optional<Category> optional=repo.findById(id);

        if(optional.isPresent())
        {
            Category category= optional.get();
            category.setName(updateCategory.getName());
            repo.save(category);
            return "Category with id "+ category.getId()+ " is updated";
        }
        else
        {
            throw new CategoryNotFoundException("Category with id "+ id+ " is not found to update");
        }
    }


    public String deleteCategoryById(Integer id)
    {
        Optional<Category> optional=repo.findById(id);
        if(optional.isPresent())
        {
		    Category category = optional.get();
		    repo.delete(category);
            return "Category with id "+ id+ " is Deleted";
        }
        else
        {
            throw new CategoryNotFoundException("Category with id "+ id+ " is not found to Delete");
        }
    }

    public List<Category> getAllCategoriesByPagination(Integer pageNo, Integer size)
    {

        PageRequest pageable = PageRequest.of(pageNo, size);
        Page<Category> pages = repo.findAll(pageable);
        List<Category> pagesList = pages.getContent();
       return pagesList;
    }
}
