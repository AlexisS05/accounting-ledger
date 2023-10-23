package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args)  {
        System.out.println("Welcome to the Accounting Ledger!");
        LocalDate dateInput;
        LocalTime timeInput;
        String inputDescription;
        String inputVendor;
        double inputAmount;

        while (true){
            System.out.println("Here is the list of commands you can do: \nD) Add Deposit \nP) Make Payment \nL) View the Ledger \nX) Exit the Ledger");
            char userInput = Utils.getCharInput();
            switch (userInput){
                case 'D':
                    System.out.println("Please provide the following information to make a deposit: ");
                    dateInput = Utils.getDate("Enter the date in (YYYY-MM-DD) or press enter for automatic");
                    timeInput = Utils.getTime("Enter the time in (HH:mm:ss) or press enter for automatic");
                    inputDescription = Utils.getStringInput("Enter the description: ");
                    inputVendor = Utils.getStringInput("Enter the vendor: ");
                    inputAmount = Utils.getDoubleInput("Enter the amount: ");

                    try (FileWriter fileWriter = new FileWriter("transactions.csv", true)) {
                        fileWriter.write(String.format("\n%s|%s|%s|%s|%.2f",
                                dateInput, timeInput, inputDescription, inputVendor, inputAmount));
                        fileWriter.close();
                        System.out.println("Deposit added successfully!");
                    } catch (IOException e) {
                        System.out.println("Error inputting data!");
                    }

                    break;
                case 'P':
                    System.out.println("Please provide the following information to make a payment: ");
                    dateInput = Utils.getDate("Enter the date in (YYYY-MM-DD) or press enter for automatic");
                    timeInput = Utils.getTime("Enter the time in (HH:mm:ss) or press enter for automatic");
                    inputDescription = Utils.getStringInput("Enter the description: ");
                    inputVendor = Utils.getStringInput("Enter the vendor: ");
                    inputAmount = Utils.getDoubleInput("Enter the amount: ");

                    try (FileWriter fileWriter = new FileWriter("transactions.csv", true)) {
                        fileWriter.write(String.format("\n%s|%s|%s|%s|%.2f",
                                dateInput, timeInput, inputDescription, inputVendor, inputAmount));
                        fileWriter.close();
                        System.out.println("Payment added successfully!");
                    } catch (IOException e) {
                        System.out.println("Error inputting data!");
                    }
                    break;
                case 'L':
                    LedgerUI.ledgerMenu();
                case 'X':
                    return;
            }
        }
    }


}