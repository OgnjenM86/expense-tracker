package com.expense.tracker;

import com.expense.tracker.models.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        printMenu();

        while (true) {

            int cmd = scanner.nextInt();
            switch (cmd) {
                case 1:
                    addExpenses();
                    break;
                case 2:
                    ListExpenses();
                    break;
                case 3:
                    System.out.println("Total spent: " + totalSpent());
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Welcome to Expense Tracker ===\n");
        System.out.print("Available commands are:");
        System.out.println("1. Add expense");
        System.out.println("2. List Expenses");
        System.out.println("3. View Total Spent");
        System.out.println("4. Exit");
        System.out.println("Choose one to proceed:");
    }

    public static void addExpenses() {
        System.out.println("Enter expense description:");
        String description = scanner.next();
        System.out.println("Enter expense amount:");
        int amount = scanner.nextInt();

        Expense e1 = new Expense();

        e1.setDescription(description);
        e1.setAmount(amount);
        expenses.add(e1); // adding object e1 to the list expenses

        System.out.println("Expense successfully saved: " + e1);
    }

    public static void ListExpenses() {
        System.out.println("Expenses:");
        System.out.println("Description\tAmount\tDate");
        for (Expense expense : expenses) {
            System.out.println(expense.getDescription() + "\t" + expense.getAmount() + "\t" + expense.getDate());
        }
    }

    public static double totalSpent() {
        double total = 0;
        for (Expense expense : expenses) {
            total = total + expense.getAmount();
        }
        return total;
    }
}
