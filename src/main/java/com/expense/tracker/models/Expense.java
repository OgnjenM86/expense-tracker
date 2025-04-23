package com.expense.tracker.models;

import java.time.LocalDate;

public class Expense {

    private String description;
    private double amount;
    private LocalDate date;

    public Expense() {
        this.date = LocalDate.now();
    }

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
