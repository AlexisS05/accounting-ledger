package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    static LocalDate dateInput;
    static LocalTime timeInput;
    static String inputDescription;
    static String inputVendor;
    static double inputAmount;

    public static void main(String[] args) {
        System.out.println("Welcome to the Accounting Ledger!");

        while (true) {
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
                case 'X':
                    return;
            }
        }
    }

    // Placeholder for now
    public static void addData(String filter) {
        System.out.println("Please provide the following information to make a " + filter + ":");
        dateInput = Utils.getDate("Enter the date in (YYYY-MM-DD) or press enter for automatic");
        timeInput = Utils.getTime("Enter the time in (HH:mm:ss) or press enter for automatic");
        inputDescription = Utils.getStringInput("Enter the description: ");
        inputVendor = Utils.getStringInput("Enter the vendor: ");
        inputAmount = Utils.getDoubleInput("Enter the amount: ");

        String formatAmount = String.format("%.2f", inputAmount);
        if (filter.equals("payment")) {
            formatAmount = "-" + formatAmount;
        }

        try (FileWriter fileWriter = new FileWriter("transactions.csv", true)) {
            fileWriter.write(String.format("\n%s|%s|%s|%s|%s",
                    dateInput, timeInput, inputDescription, inputVendor, formatAmount));
            fileWriter.close();
            System.out.println(filter + " added successfully!");
        } catch (IOException e) {
            System.out.println("Error inputting data!");
        }
    }


}