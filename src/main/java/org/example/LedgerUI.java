package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
        boolean data;
        while (true) {
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
                        data = transaction.getInputAmount() > 0;

                        if (data) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case 'P':
                    for (Transaction transaction : transactions) {
                        data = transaction.getInputAmount() < 0;

                        if (data) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case 'R':
                    reportsMenu();
                    break;
                case 'H':
                    return;
                default:
                    System.out.println("This is not a valid option. Please try again.");
            }
        }
    }

    public static void reportsMenu() {
        int date;
        int date2;
        int year;
        int year2;
        while (true) {
            System.out.println("REPORTS MENU");
            System.out.println("1) Month To Date  \n2) Previous Month \n3) Year To Date \n4) Previous Year \n5) Search By Vendor \n6) Custom Search \n7) Return to Ledger");
            LocalDate now = LocalDate.now();
            char input = Utils.getCharInput();
            switch (input) {
                case '1':
                    for (Transaction transaction : transactions) {
                        date = transaction.getDateInput().getMonthValue();
                        date2 = now.getMonthValue();
                        year = transaction.getDateInput().getYear();
                        year2 = now.getYear();
                        // Compare it? date == date2
                        if (date == date2 && year == year2) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case '2':
                    for (Transaction transaction : transactions) {
                        date = transaction.getDateInput().getMonthValue();
                        date2 = now.minusMonths(1).getMonthValue();
                        year = transaction.getDateInput().getYear();
                        year2 = now.getYear();
                        // Compare it? date == date2
                        if (date == date2 && year == year2) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case '3':
                    for (Transaction transaction : transactions) {
                        date = transaction.getDateInput().getYear();
                        date2 = now.getYear();
                        if (date == date2) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case '4':
                    for (Transaction transaction : transactions) {
                        date = transaction.getDateInput().getYear();
                        date2 = now.minusYears(1).getYear();
                        // Compare it? date == date2
                        if (date == date2) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case '5':
                    String inputVendor = Utils.getStringInput("Enter Vendor Name: ");
                    for (Transaction transaction : transactions
                    ) {
                        if(transaction.inputVendor().contains(inputVendor)){
                            System.out.println(transaction);
                        }
                    }
                    break;
                case '6':
                    // Custom Search
                    System.out.println("Custom Search: ");
                    LocalDate dateInput = Utils.getDate("Enter a date");
                case '7':
                    return;
            }
        }
    }
}
