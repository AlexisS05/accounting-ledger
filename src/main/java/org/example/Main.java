package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Accounting Ledger!");

        while (true){
            System.out.println("Here is the list of commands you can do: \nD) Add Deposit \nP) Make Payment \nL) View the Ledger \nX) Exit the Ledger");
            char userInput = Utils.getCharInput();
            LocalDate dateInput;
            LocalTime timeInput;
            String inputDescription;
            String inputVendor;
            double inputAmount;
            switch (userInput){
                case 'D':
                    System.out.println("Please provide the following information to make a deposit: ");
                    // Placeholder for now
                    dateInput = LocalDate.now();
                    timeInput = LocalTime.now();
                    inputDescription = "";
                    inputVendor = "";
                    inputAmount = 0;
                    break;
                case 'P':
                    System.out.println("Please provide the following information to make a payment: ");
                    // Placeholder for now
                    dateInput = LocalDate.now();
                    timeInput = LocalTime.now();
                    inputDescription = "";
                    inputVendor = "";
                    inputAmount = 0;
                    break;
                case 'L':
                    LedgerUI.ledgerMenu();
                case 'X':
                    return;
            }
        }
    }


}