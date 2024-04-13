package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.CategoryDto;
import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

    public Collection<CategoryDto> findAll(){
        Collection<Category> collection = this.categoryRepository.findAll();
        Collection<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : collection) {
            categoryDtos.add(new CategoryDto(category));
        }

        return categoryDtos;
    }

    public CategoryDto post(Category category) {
        Category updatedCategory = this.categoryRepository.save(category);
        return new CategoryDto(updatedCategory);
    }
}
