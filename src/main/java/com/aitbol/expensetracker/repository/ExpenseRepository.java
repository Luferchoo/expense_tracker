package com.aitbol.expensetracker.repository;

import com.aitbol.expensetracker.model.entity.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense,String> {

    @Query("SELECT e from Expense e where e.name=:name")
    Optional<Expense> findByName(String name);

    Collection<Expense> findAll();

    Expense save(Expense expense);

    void deleteByName(String name);
}
