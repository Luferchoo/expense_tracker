package com.aitbol.expensetracker.repository;

import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.model.entity.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {
    Optional<Category> findById(Long Id);

    Optional<Category> findByName(String name);

    Collection<Category> findAll();

    Category save(Category category);

    void deleteByName(String name);
}
