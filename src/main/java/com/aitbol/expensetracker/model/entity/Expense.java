package com.aitbol.expensetracker.model.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Expense {
    public Expense(){

    }
    public Expense(String name, String description, double amount, Timestamp date) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Id
    private String name;
    private String description;
    private double amount;
    private Timestamp date;

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
