package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class LedgerUI {
    public static ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] data = input.split("\\|");

                Transaction transaction = new Transaction(LocalDate.parse(data[0]), LocalTime.parse(data[1]), data[2], data[3], Double.parseDouble(data[4]));
                transactions.add(transaction);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Sort our transactions ArrayList in latest to oldest
        Comparator<Transaction> compareByDate = Comparator.comparing(Transaction::getDate).reversed();
        Comparator<Transaction> compareByTime = Comparator.comparing(Transaction::getTime).reversed();
//        Comparator<Transaction> compareByDescription = Comparator.comparing(Transaction::getDescription).reversed(); // Sort Alphabetically?
//        transactions.sort(compareByDescription);
        transactions.sort(compareByDate.thenComparing(compareByTime)); // List by date then time

        return transactions;
    }

    public static ArrayList<Transaction> transactions = getTransactions();

    public static void ledgerMenu() {
        while (true) {
            System.out.print("\u001B[31m");
            System.out.printf("%20s\n", "LEDGER MENU");
            System.out.print("\u001B[0m");
            System.out.println("A) Display all entries \nD) Display deposits \nP) Display payments \nR) Go to Reports \nH) Return Home");
            char input = Utils.getCharInput();
            switch (input) {
                case 'A':
                    displayEntries("Entries");
                    break;
                case 'D':
                    displayEntries("Deposits");
                    break;
                case 'P':
                    displayEntries("Payments");
                    break;
                case 'R':
                    Reports.reportsMenu();
                    break;
                case 'H':
                    return;
                default:
                    System.out.println("This is not a valid option. Please try again.");
            }
        }
    }

    public static void displayEntries(String filter) {
        boolean data; // Always true to show data
        printTransactionTitle();
        for (Transaction transaction : transactions) { // Handles if true data will show
            if (filter.equals("Entries")) {
                printTransaction(transaction);
            } else if (filter.equals("Deposits")) {
                data = transaction.getAmount() > 0;
                if (data) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Payments")) {
                data = transaction.getAmount() < 0;
                if (data) {
                    printTransaction(transaction);
                }
            }
        }
    }

    public static void printTransaction(Transaction transaction) { // To print data
        System.out.printf("%-15s %-15s %-30s %-25s %15.2f\n",
                transaction.getDate(),
                transaction.getTime(),
                transaction.getDescription(),
                transaction.getVendor(),
                transaction.getAmount()
        );
    }

    public static void printTransactionTitle() { // To print titles
        System.out.printf("%-15s %-15s %-30s %-25s %15s\n",
                "Date",
                "Time",
                "Description",
                "Vendor",
                "Amount"
        );
    }
}
