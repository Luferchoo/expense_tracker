package com.aitbol.expensetracker.model.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Expense expense = (Expense) o;
        return getName() != null && Objects.equals(getName(), expense.getName());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                .hashCode() : getClass().hashCode();
    }
}
