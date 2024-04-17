package com.aitbol.expensetracker.controller;

import com.aitbol.expensetracker.model.dto.CategoryDto;
import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(CategoryService service) {
        this.categoryService = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/{id}", produces = { "application/json" })
    public CategoryDto getCategory(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "category/all", produces = { "application/json" })
    public Collection<CategoryDto> getAllCategories(){
        return categoryService.findAll();
    }

    @PostMapping("categoryPost/")
    public CategoryDto postCategory(@RequestBody Category category) {
        return categoryService.post(category);
    }

    @PutMapping("categoryUpdate/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/category/{name}")
    public CategoryDto deleteExpense(@PathVariable String name) {
        return categoryService.delete(name);
    }
}
