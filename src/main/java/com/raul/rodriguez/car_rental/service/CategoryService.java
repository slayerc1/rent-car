package com.raul.rodriguez.car_rental.service;

import com.raul.rodriguez.car_rental.entity.Category;

import org.springframework.data.domain.Page;

public interface CategoryService {

    public abstract Page<Category> getCategories(Integer page);

    public abstract Category getCategory(String name);

    public abstract Category addCategory(Category category);

    public abstract Category updateCategory(Category editCategory, String name);

    public abstract void deleteCategory(String name);

}
