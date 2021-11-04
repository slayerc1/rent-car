package com.raul.rodriguez.car_rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.raul.rodriguez.car_rental.entity.Category;
import com.raul.rodriguez.car_rental.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    @Qualifier("CategoryService")
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(@RequestParam Integer page) {
        return categoryService.getCategories(page).getContent();
    }

    @GetMapping(value = "/{name}")
    public Category getCategory(@PathVariable(required = true) String name) {
        return categoryService.getCategory(name);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping(value = "/{name}")
    public Category updateCategory(@RequestBody Category editCategory, @PathVariable(required = true) String name) {
        return categoryService.updateCategory(editCategory, name);
    }

    @DeleteMapping(value = "/{name}")
    public void deleteCategory(@PathVariable(required = true) String name) {
        categoryService.deleteCategory(name);
    }
}
