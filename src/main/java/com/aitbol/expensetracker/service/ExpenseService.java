package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

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

}
