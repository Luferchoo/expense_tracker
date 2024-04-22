package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.CategoryDto;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.CategoryRepository;
import com.aitbol.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    public CategoryDto findById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.map(CategoryDto::new).orElse(null);
    }

    public Collection<CategoryDto> findAll() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categoryDtos.add(new CategoryDto(category)));
        return categoryDtos;
    }

    public CategoryDto post(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryDto(savedCategory);
    }

    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category existingCategory = optional.get();
            existingCategory.setName(categoryDto.getName());
            existingCategory.setDescription(categoryDto.getDescription());
            Category updatedCategory = categoryRepository.save(existingCategory);
            return new CategoryDto(updatedCategory);
        } else {
            return null;
        }
    }

    @Transactional
    public CategoryDto delete(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category deletedCategory = optional.get();
            // Desvincular los gastos asociados a esta categoría
            unlinkExpensesFromCategory(deletedCategory);
            categoryRepository.deleteById(id);
            return new CategoryDto(deletedCategory);
        } else {
            return null;
        }
    }

    private void unlinkExpensesFromCategory(Category category) {
        // Obtener todos los gastos asociados a esta categoría
        List<Expense> expenses = category.getExpenses();
        // Desvincular la categoría de cada gasto asociado
        expenses.forEach(expense -> expense.getCategories().remove(category));
    }

    public Collection<CategoryDto> getCategoryExpenses(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElse(null);
        if (expense != null) {
            List<CategoryDto> categoryDtos = new ArrayList<>();
            expense.getCategories().forEach(category -> categoryDtos.add(new CategoryDto(category)));
            return categoryDtos;
        }
        return null;
    }
}
