package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public ExpenseDto findByName(String name){
        Optional<Expense> optional = this.expenseRepository.findByName(name);
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

    @Transactional
    public ExpenseDto update(String name, Expense expense) {
        Optional<Expense> optional = this.expenseRepository.findByName(name);
        if (optional.isPresent()) {
            Expense existingExpense = optional.get();
            existingExpense.setDescription(expense.getDescription());
            Expense updatedExpense = this.expenseRepository.save(existingExpense);
            return new ExpenseDto(updatedExpense);
        } else {
            return null;
        }
    }
}
