package com.aitbol.expensetracker.controller;
import com.aitbol.expensetracker.model.dto.ExpenseDto;
import com.aitbol.expensetracker.model.entity.Expense;
import com.aitbol.expensetracker.service.ExpenseService;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ExpenseController {

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService service){
        this.expenseService = service;
    }
    @RequestMapping(method = RequestMethod.GET, value = "expense/{id}", produces = { "application/json" })
    public ExpenseDto getExpense(@PathVariable("id") Long id){
        return expenseService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "expense/all", produces = { "application/json" })
    public Collection<ExpenseDto> getAllExpenses(){
        return expenseService.findAll();
    }

    @PostMapping("expensepost/")
    public ExpenseDto postExpense(@RequestBody ExpenseDto expenseDto) {
        return expenseService.post(expenseDto);
    }

    @PutMapping("expenseupdate/{id}")
    public ExpenseDto updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expense) {
        return expenseService.update(id, expense);
    }

    @DeleteMapping("/expenses/{id}")
    public ExpenseDto deleteExpense(@PathVariable Long id) {
        return expenseService.delete(id);
    }


}
