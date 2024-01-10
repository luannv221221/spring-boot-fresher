package com.ra.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Boolean save(Category category);
    Category findById(Long id);
    void delete(Long id);
}
