package com.expense.tracker;

import com.expense.tracker.models.Expense;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static final String CSV_FILE = "Expenses.csv";

    public static void main(String[] args) throws IOException {
        printMenu();

        while (true) {
            int cmd = nextIntCommand();
            switch (cmd) {
                case 0:
                    System.out.println("Waiting for your command...");
                    break;
                case 1:
                    if (fileExists()) {
                        createFile();
                    }
                    addExpenses();
                    break;
                case 2:
                    if (fileExists()) {
                        createFile();
                        System.out.println("you don't have any expenses");
                    } else {
                        listExpenses();
                    }
                    break;
                case 3:
                    if (fileExists()) {
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

        double amount = nextDoubleCommand();
        if (Double.MIN_VALUE == amount) {
            System.out.println("Invalid amount. Please enter a valid decimal number.");
            System.out.println("For example: 12.34 or 100");
            return;
        }

        Expense expense = new Expense(description, amount);

        try {
            var newRow = expense + System.lineSeparator();
            Files.write(Path.of(CSV_FILE), newRow.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
        System.out.println("Expense added successfully: " + expense);
    }

    private static void listExpenses() throws IOException {
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

    private static void createFile() throws IOException {
        Files.writeString(Paths.get(CSV_FILE), "description;amount;date;" + System.lineSeparator());
    }

    private static boolean fileExists() {
        return !Files.exists(Paths.get(CSV_FILE));
    }

    private static String readFile() throws IOException {
        return Files.readString(Path.of(CSV_FILE));
    }

    private static int nextIntCommand() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number from 1 to 4");
            return 0; // Default to exit if input is invalid
        }
    }

    private static Double nextDoubleCommand() {
        try {
            return Double.parseDouble(scanner.next());
        } catch (NumberFormatException e) {
            return Double.MIN_VALUE;
        }
    }
}

