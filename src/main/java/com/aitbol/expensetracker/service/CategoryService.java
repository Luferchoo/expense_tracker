package com.aitbol.expensetracker.service;

import com.aitbol.expensetracker.model.dto.CategoryDto;
import com.aitbol.expensetracker.model.entity.Category;
import com.aitbol.expensetracker.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto findById(Long id){
        Optional<Category> optional = this.categoryRepository.findById(id);
        if (optional.isPresent()) {
            return new CategoryDto(optional.get());
        } else {
            return null;
        }
    }

    public Collection<CategoryDto> findAll(){
        Collection<Category> collection = this.categoryRepository.findAll();
        Collection<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : collection) {
            categoryDtos.add(new CategoryDto(category));
        }

        return categoryDtos;
    }

    public CategoryDto post(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = this.categoryRepository.save(category);
        return new CategoryDto(updatedCategory);
    }

    public CategoryDto update(Long id, CategoryDto category) {
        Optional<Category> optional = this.categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category existingCategory = optional.get();
            existingCategory.setDescription(category.getDescription());
            Category updatedCategory = this.categoryRepository.save(existingCategory);
            return new CategoryDto(updatedCategory);
        } else {
            return null;
        }
    }

    @Transactional
    public CategoryDto delete(Long id) {
        Optional<Category> optional = this.categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category deletedCategory = optional.get();
            this.categoryRepository.deleteById(id);
            return new CategoryDto(deletedCategory);
        } else {
            return null;
        }
    }
}
