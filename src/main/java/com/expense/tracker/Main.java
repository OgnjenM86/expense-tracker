package com.expense.tracker;

import com.expense.tracker.models.Expense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.OptionalDouble;
import java.util.Scanner;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static final String CSV_FILE = "Expenses.csv";

    public static void main(String[] args) {
        printMenu();

        while (true) {
            int cmd = nextIntCommand();
            switch (cmd) {
                case 0:
                    System.out.println("Waiting for your command...");
                    break;
                case 1:
                    if (missingCsvFile()) {
                        createFile();
                    }
                    addExpenses();
                    break;
                case 2:
                    if (missingCsvFile()) {
                        createFile();
                        System.out.println("you don't have any expenses");
                    } else {
                        listExpenses();
                    }
                    break;
                case 3:
                    if (missingCsvFile()) {
                        createFile();
                        System.out.println("You don't have any spending.");
                    } else {
                        System.out.println("Total spent: " + totalSpent());
                    }
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Welcome to Expense Tracker ===\n");
        System.out.print("Available commands are:\n");
        System.out.println("1. Add expense");
        System.out.println("2. List Expenses");
        System.out.println("3. View Total Spent");
        System.out.println("4. Exit");
        System.out.println("Choose one to proceed:\n");
    }

    private static void addExpenses() {
        System.out.println("Enter expense description:");
        String description = scanner.next();
        System.out.println("Enter expense amount:");

        OptionalDouble amount = nextDoubleCommand();
        if (amount.isEmpty()) {
            System.out.println("Invalid amount. Please enter a valid decimal number.");
            System.out.println("For example: 12.34 or 100");
            return;
        }

        Expense expense = new Expense(description, amount.getAsDouble());

        try {
            var newRow = expense + System.lineSeparator();
            Files.write(Path.of(CSV_FILE), newRow.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
        System.out.println("Expense added successfully: " + expense);
    }

    private static void listExpenses() {
        System.out.println("Expenses:");

        String fileContent = readFile();
        String[] rows = fileContent.split(System.lineSeparator());
        for (String row : rows) {

            row = row.replace(";", "\t");

            System.out.println(row);
        }
    }

    private static double totalSpent() {
        double total = 0;
        // TODO:
        // 1) read csv file content
        // 2) split by lines - System.lineSeparator() = String[]
        // 3) skip the first line (header)
        // 4) for each line, split by ';' and get the amount
        // 5) sum all amounts and return the total

        return total;
    }

    private static void createFile() {
        try {
            Files.writeString(Paths.get(CSV_FILE), "description;amount;date;" + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error occurred while creating csv file", e);
        }
    }

    private static boolean missingCsvFile() {
        return !Files.exists(Paths.get(CSV_FILE));
    }

    private static String readFile() {
        try {
            return Files.readString(Path.of(CSV_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error occurred while reading csf file", e);
        }
    }

    private static int nextIntCommand() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number from 1 to 4");
            return 0; // Default to exit if input is invalid
        }
    }

    private static OptionalDouble nextDoubleCommand() {
        try {
            return OptionalDouble.of(Double.parseDouble(scanner.next()));
        } catch (NumberFormatException e) {
            return OptionalDouble.empty();
        }
    }
}

