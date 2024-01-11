package com.ra.service;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Boolean saveOrUpdate(Product product);
    Product findById(Long id);
    void delete(Long id);
}
