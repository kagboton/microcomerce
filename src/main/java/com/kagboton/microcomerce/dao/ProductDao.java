package com.kagboton.microcomerce.dao;

import com.kagboton.microcomerce.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();
    public Product findByID(int id);
    public Product save(Product product);
}
