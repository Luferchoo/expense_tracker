package com.aitbol.expensetracker.controller;

import com.aitbol.expensetracker.model.dto.CategoryDto;
import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.service.CategoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService service) {
        this.categoryService = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/{name}", produces = { "application/json" })
    public CategoryDto getCategory(@PathVariable("name") String name){
        return categoryService.findByName(name);
    }
    @RequestMapping(method = RequestMethod.GET, value = "category/all", produces = { "application/json" })
    public Collection<CategoryDto> getAllCategories(){
        return categoryService.findAll();
    }
}
