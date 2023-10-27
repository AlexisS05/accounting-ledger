package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        Utils.createDefaultCSV("transactions.csv");
        System.out.println("Welcome to the Accounting Ledger!");
        homeMenu();
    }

    public static void homeMenu(){
        while (true) {
            System.out.print("\u001B[31m");
            System.out.printf("%20s\n", "HOME MENU");
            System.out.print("\u001B[0m");
            System.out.println("Here is the list of commands you can do: \nD) Add Deposit \nP) Make Payment \nL) View the Ledger \nX) Exit the Ledger");
            char userInput = Utils.getCharInput();
            switch (userInput) {
                case 'D':
                    addData("deposit");
                    break;
                case 'P':
                    addData("payment");
                    break;
                case 'L':
                    LedgerUI.ledgerMenu();
                    break;
                case 'X':
                    return;
                default:
                    System.out.println("This is not a valid option. Please try again.");
            }
        }
    }

    public static void addData(String filter) {
        System.out.println("Please provide the following information to make a " + filter + ":");
        LocalDate dateInput = Utils.getDate("Enter the date in (YYYY-MM-DD) or press enter for automatic");
        LocalTime timeInput = Utils.getTime("Enter the time in (HH:mm:ss) or press enter for automatic");
        String inputDescription = Utils.getStringInput("Enter the description: ");
        String inputVendor = Utils.getStringInput("Enter the vendor: ");
        double inputAmount = Utils.getDoubleInput("Enter the amount: ");

        String formatAmount = String.format("%.2f", inputAmount);
        if (filter.equals("payment")) {
            formatAmount = "-" + formatAmount;
        }

        writeToCSV("transactions.csv", dateInput, timeInput, inputDescription, inputVendor, formatAmount);
        System.out.println(filter + " added successfully!");
    }
    public static void writeToCSV(String fileName, LocalDate dateInput, LocalTime timeInput, String inputDescription, String inputVendor, String formatAmount) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%s",
                    dateInput, timeInput, inputDescription, inputVendor, formatAmount));
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error adding data!");
        }
    }
}