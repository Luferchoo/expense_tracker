package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.dto.CategoryDto;


@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }


    public ExpenseDto findById(Long id){
        Optional<Expense> optional = this.expenseRepository.findById(id);
        if (optional.isPresent()) {
            return new ExpenseDto(optional.get());
        } else {
            return null;
        }
    }
    public Collection<ExpenseDto> findAll(){
        Collection<Expense> collection = this.expenseRepository.findAll();
        Collection<ExpenseDto> expenseDtos = new ArrayList<>();

        for (Expense expense : collection) {
            expenseDtos.add(new ExpenseDto(expense));
        }

        return expenseDtos;
    }

    public ExpenseDto post(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setName(expenseDto.getName());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        if (expenseDto.getTimestamp() == null) {
            expense.setTimestamp(new Date());
        } else {
            expense.setTimestamp(expenseDto.getTimestamp());
        }
        Expense updatedExpense = this.expenseRepository.save(expense);
        return new ExpenseDto(updatedExpense);
    }


    public ExpenseDto update(Long id, ExpenseDto expenseDto) {
        Optional<Expense> optional = this.expenseRepository.findById(id);
        if (optional.isPresent()) {
            Expense existingExpense = optional.get();
            existingExpense.setName(expenseDto.getName());
            existingExpense.setDescription(expenseDto.getDescription());
            existingExpense.setAmount(expenseDto.getAmount());
            existingExpense.setTimestamp(expenseDto.getTimestamp());
            Expense updatedExpense = this.expenseRepository.save(existingExpense);
            return new ExpenseDto(updatedExpense);
        } else {
            return null;
        }
    }

    @Transactional
    public ExpenseDto delete(Long id) {
        Optional<Expense> optional = this.expenseRepository.findById(id);
        if (optional.isPresent()) {
            Expense deletedExpense = optional.get();
            this.expenseRepository.deleteById(id);
            return new ExpenseDto(deletedExpense);
        } else {
            return null;
        }
    }

    public Collection<CategoryDto> getCategoryExpenses(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElse(null);
        if (expense != null) {
            Collection<Category> categories = expense.getCategories();
            Collection<CategoryDto> categoryDtos = new ArrayList<>();

            for (Category category : categories) {
                categoryDtos.add(new CategoryDto(category));
            }

            return categoryDtos;
        }
        return null;
    }
}
