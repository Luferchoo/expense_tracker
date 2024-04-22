package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.ExpenseRepository;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.dto.CategoryDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseDto findById(Long id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        return optional.map(ExpenseDto::new).orElse(null);
    }

    public Collection<ExpenseDto> findAll() {
        List<ExpenseDto> expenseDtos = new ArrayList<>();
        expenseRepository.findAll().forEach(expense -> expenseDtos.add(new ExpenseDto(expense)));
        return expenseDtos;
    }

    public ExpenseDto post(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setName(expenseDto.getName());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setTimestamp(expenseDto.getTimestamp() != null ? expenseDto.getTimestamp() : new Date());
        Expense savedExpense = expenseRepository.save(expense);
        return new ExpenseDto(savedExpense);
    }

    public ExpenseDto update(Long id, ExpenseDto expenseDto) {
        Optional<Expense> optional = expenseRepository.findById(id);
        if (optional.isPresent()) {
            Expense existingExpense = optional.get();
            existingExpense.setName(expenseDto.getName());
            existingExpense.setDescription(expenseDto.getDescription());
            existingExpense.setAmount(expenseDto.getAmount());
            existingExpense.setTimestamp(expenseDto.getTimestamp());
            Expense updatedExpense = expenseRepository.save(existingExpense);
            return new ExpenseDto(updatedExpense);
        } else {
            return null;
        }
    }

    @Transactional
    public ExpenseDto delete(Long id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        if (optional.isPresent()) {
            Expense deletedExpense = optional.get();
            // Desvincular el gasto de las categorías asociadas
            unlinkCategoriesFromExpense(deletedExpense);
            expenseRepository.deleteById(id);
            return new ExpenseDto(deletedExpense);
        } else {
            return null;
        }
    }

    private void unlinkCategoriesFromExpense(Expense expense) {
        // Obtener todas las categorías asociadas a este gasto
        Collection<Category> categories = expense.getCategories();
        // Desvincular el gasto de cada categoría asociada
        categories.forEach(category -> category.getExpenses().remove(expense));
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
