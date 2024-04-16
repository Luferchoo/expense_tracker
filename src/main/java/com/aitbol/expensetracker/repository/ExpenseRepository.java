package com.aitbol.expensetracker.repository;

import com.aitbol.expensetracker.model.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense,String> {

    Optional<Expense> findById(Long id);

    Optional<Expense> findByName(String name);

    Collection<Expense> findAll();

    Expense save(Expense expense);

    void deleteById(Long id);
}
