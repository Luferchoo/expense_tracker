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
    @RequestMapping(method = RequestMethod.GET, value = "expense/{name}", produces = { "application/json" })
    public ExpenseDto getExpense(@PathVariable("name") String name){
        return expenseService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "expense/all", produces = { "application/json" })
    public Collection<ExpenseDto> getAllExpenses(){
        return expenseService.findAll();
    }

    @PostMapping("expensepost/")
    public ExpenseDto postExpense(@RequestBody Expense expense) {
        return expenseService.post(expense);
    }

    @PutMapping("expenseupdate/{name}")
    public ExpenseDto updateExpense(@PathVariable String name, @RequestBody Expense expense) {
        return expenseService.update(name, expense);
    }

    @DeleteMapping("/expenses/{name}")
    public ExpenseDto deleteExpense(@PathVariable String name) {
        return expenseService.delete(name);
    }
}
