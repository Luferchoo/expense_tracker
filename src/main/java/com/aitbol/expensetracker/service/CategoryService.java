package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.CategoryDto;
import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto findByName(String name){
        Optional<Category> optional = this.categoryRepository.findByName(name);
        if (optional.isPresent()) {
            return new CategoryDto(optional.get());
        } else {
            return null;
        }
    }
}
