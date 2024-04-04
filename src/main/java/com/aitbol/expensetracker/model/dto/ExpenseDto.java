package com.aitbol.expensetracker.model.dto;

import java.sql.Timestamp;

public class ExpenseDto {
    private String name;
    private String description;
    private double amount;
    private Timestamp date;

    public ExpenseDto() {

    }
    public ExpenseDto(String name, String description, double amount, Timestamp date) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
