package com.expense.tracker.models;

import java.time.LocalDate;

/**
 * Expense is immutable class that represents an expense.
 */
public class Expense {

    private final LocalDate date;
    private final String description;
    private final double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Description: %s;Amount: %.2f; Date: %s", description, amount, date);
    }
}
