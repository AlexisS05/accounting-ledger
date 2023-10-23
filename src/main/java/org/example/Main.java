package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Accounting Ledger!");

        while (true){
            System.out.println("Here is the list of commands you can do: \n D) Add Deposit \n P) Make Payment \n L) View the Ledger \n X) Exit the Ledger");
            char userInput = Utils.getCharInput();
            switch (userInput){
                case 'D':
                    System.out.println("Please provide the following information to make a deposit: ");
                    // Placeholder for now
                    LocalDate dateInput = LocalDate.now();
                    LocalTime timeInput = LocalTime.now();
                    String inputDescription = "";
                    String inputVendor = "";
                    double inputAmount = 0;
            }
        }
    }


}