package com.pinnacle.app.service;

import com.pinnacle.app.dao.ProductRepository;
import com.pinnacle.app.exception.ProductNotFoundException;
import com.pinnacle.app.model.Category;
import com.pinnacle.app.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService
{
    @Autowired
private ProductRepository repo;

    public Product registerProduct(Product product)
    {
        return repo.save(product);
    }

    public String updateProduct(Integer id, Product updateProduct)
    {

        Optional<Product> optional=repo.findById(id);

        if(optional.isPresent())
        {
            Product product = optional.get();
            product.setName(updateProduct.getName());
            product.setDescription(updateProduct.getDescription());
            product.setPrice(updateProduct.getPrice());

            repo.save(product);
            return "Product with id "+ id+ " is updated";
        }
        else
        {
            throw new ProductNotFoundException("Product with id "+ id+ " is not found to update");
        }

    }

    public Product getProductById(Integer id)
    {
        return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

    }

    @Transactional
    public String deleteProductById(Integer id)
    {

        Optional<Product> optional=repo.findById(id);
        if(optional.isPresent())
        {
            repo.deleteById(id);
            return "Product with id "+ id+ " is Deleted";
        }
        else
        {
            throw new ProductNotFoundException("Product with id "+ id+ " is not found to Delete");
        }
    }

    public List<Product> getProductsByPagination(int pageNo, Integer size)
    {
        PageRequest pageable = PageRequest.of(pageNo, size);
        Page<Product> pages = repo.findAll(pageable);
        return pages.getContent();
    }
}
