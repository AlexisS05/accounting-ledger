package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerUI {
//    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] data = input.split("\\|");

                // Ledger ledger = new Ledger(LocalDate.parse(data[0]), LocalTime.parse(data[1]), data[2], data[3], Double.parseDouble(data[4]));
                Transaction transaction = new Transaction(LocalDate.parse(data[0]), LocalTime.parse(data[1]), data[2], data[3], Double.parseDouble(data[4]));
                transactions.add(transaction);
                System.out.println(transactions);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public static ArrayList<Transaction> transactions = getTransactions();

    public static void ledgerMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("LEDGER MENU");
            System.out.println("A) Display all entries \nD) Display deposits \nP) Display payments \nR) Go to Reports \nH) Return Home");
            char input = Utils.getCharInput();
            switch (input) {
                case 'A':
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                    break;
                case 'D':
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction.getInputAmount());
                    }
                    break;
                case 'P':
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction.getInputAmount() < 0);
                    }
                    break;
                case 'R':
                    ReportsMenu();
                case 'H':
                    return;
            }
            done = true;
        }
    }

    public static void ReportsMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("REPORTS MENU");
            System.out.println("1) Month To Date  \nD) Previous Month \nP) Year To Date \nR) Previous Year \nH) Search By Vendor");
            char input = Utils.getCharInput();
            LocalDate now = LocalDate.now();
            switch (input) {
                case 'A':
                    for(Transaction transaction : transactions){
                        System.out.println(transaction.dateInput().getMonth());
                    }
                case 'D':

                case 'P':

                case 'R':

                case 'H':

            }
            done = true;
        }
    }
}
