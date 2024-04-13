package com.aitbol.expensetracker.repository;

import com.aitbol.expensetracker.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {
    Optional<Category> findByName(String name);
}
