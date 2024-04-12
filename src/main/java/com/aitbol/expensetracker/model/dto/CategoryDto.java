package com.aitbol.expensetracker.model.dto;

import com.aitbol.expensetracker.model.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDto {
    private String name;
    private String description;

    public CategoryDto() {

    }

    public CategoryDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
