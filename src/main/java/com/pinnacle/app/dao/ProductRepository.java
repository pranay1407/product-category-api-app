package com.pinnacle.app.dao;

import com.pinnacle.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>
{

}
