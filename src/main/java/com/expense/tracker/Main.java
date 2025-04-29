package com.expense.tracker;

import com.expense.tracker.models.Expense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static final String CSV_FILE = "Expenses.csv";
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        printMenu();

        while (true) {
            int cmd = scanner.nextInt();
            switch (cmd) {
                case 1:
                    if (!doesCsvFileExists()) {
                        createFile();
                    }
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

        double amount = Double.parseDouble(scanner.next());

        Expense e1 = new Expense(description, amount);

        try {
            var newRow = e1 + System.lineSeparator();
            Files.write(Path.of(CSV_FILE), newRow.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Expense added successfully: " + e1);
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

    public static void createFile() throws IOException {
        Files.writeString(Paths.get(CSV_FILE), "description;amount;date;" + System.lineSeparator());
    }

    public static boolean doesCsvFileExists() {
        return Files.exists(Paths.get(CSV_FILE));
    }
}

