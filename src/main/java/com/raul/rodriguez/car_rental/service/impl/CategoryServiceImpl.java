package com.raul.rodriguez.car_rental.service.impl;

import com.raul.rodriguez.car_rental.entity.Category;
import com.raul.rodriguez.car_rental.exceptions.CategoryNotFoundException;
import com.raul.rodriguez.car_rental.repository.CategoryRepository;
import com.raul.rodriguez.car_rental.service.CategoryService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    private static final Log LOG = LogFactory.getLog(CategoryServiceImpl.class);

    @Autowired
    @Qualifier("categoryRepository")
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getCategories(Integer page) {
        LOG.info("METHOD: getCategorys -- PARAMS: " + page);
        return categoryRepository.findAll(PageRequest.of(page, 100));
    }

    @Override
    public Category getCategory(String name) {
        LOG.info("METHOD: getCategory -- PARAMS: " + name);
        return categoryRepository.findById(name).orElseThrow(() -> new CategoryNotFoundException(name));
    }

    @Override
    public Category addCategory(Category category) {
        LOG.info("METHOD: addCategory -- PARAMS: " + category);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category editCategory, String name) {
        LOG.info("METHOD: updateCategory -- PARAMS: " + editCategory + " / " + name);
        return categoryRepository.findById(name).map(category -> {
            category.setName(editCategory.getName());
            category.setPrice(editCategory.getPrice());
            category.setExtraDayPrice(editCategory.getExtraDayPrice());
            category.setExtraDayPct(editCategory.getExtraDayPct());
            category.setLoyaltyPoints(editCategory.getLoyaltyPoints());
            return categoryRepository.save(category);
        }).orElseGet(() -> {
            editCategory.setName(name);
            return categoryRepository.save(editCategory);
        });
    }

    @Override
    public void deleteCategory(String name) {
        LOG.info("METHOD: deleteCategory -- PARAMS: " + name);
        categoryRepository.deleteById(name);
    }
}
