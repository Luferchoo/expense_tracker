package com.aitbol.expensetracker.model.dto;

import com.aitbol.expensetracker.model.entity.Expense;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;

public class ExpenseDto {
    private String name;
    private String description;
    private double amount;
    private Date timestamp;

    public ExpenseDto() {

    }
    public ExpenseDto(String name, String description, double amount, Date timestamp) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public ExpenseDto(Expense expense){
        this.name = expense.getName();
        this.description = expense.getDescription();
        this.amount = expense.getAmount();
        this.timestamp = expense.getDate();
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
    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    @JsonProperty("timestamp")
    public Date getDate() {
        return timestamp;
    }

    public void setDate(Date timestamp) {
        this.timestamp = timestamp;
    }
}
