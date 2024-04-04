package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

}
