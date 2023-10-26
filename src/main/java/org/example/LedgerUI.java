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
            Utils.createDefaultCSV();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Sort our transactions ArrayList in ascending order before returning it
        Comparator<Transaction> compareByDate = Comparator.comparing(Transaction::getDateInput).reversed();
        Comparator<Transaction> compareByTime = Comparator.comparing(Transaction::getTimeInput).reversed();
        transactions.sort(compareByDate.thenComparing(compareByTime));

        return transactions;
    }

    public static ArrayList<Transaction> transactions = getTransactions();

    public static void ledgerMenu() {
        while (true) {
            System.out.println("LEDGER MENU");
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
                    reportsMenu();
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
        for (Transaction transaction : transactions) {
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

    public static void reportsMenu() {
        while (true) {
            System.out.println("REPORTS MENU");
            System.out.println("1) Month To Date  \n2) Previous Month \n3) Year To Date \n4) Previous Year \n5) Search By Vendor \n6) Custom Search \n7) Return to Ledger");
            String vendor = "";
            char input = Utils.getCharInput();
            switch (input) {
                case '1':
                    displayReports("Month To Date", vendor);
                    break;
                case '2':
                    displayReports("Previous Month", vendor);
                    break;
                case '3':
                    displayReports("Year To Date", vendor);
                    break;
                case '4':
                    displayReports("Previous Year", vendor);
                    break;
                case '5':
                    String inputVendor = Utils.getStringInput("Enter Vendor Name: ").toLowerCase();
                    displayReports("", inputVendor);
                    break;
                case '6':
                    customSearch();
                    break;
                case '7':
                    return;
            }
        }
    }

    public static void displayReports(String filter, String vendor) {
        int date;
        int date2;
        int year;
        int year2;
        if (!vendor.isEmpty()) {
            filter = vendor;
        }
        LocalDate now = LocalDate.now();

        for (Transaction transaction : transactions) {
            if (filter.equals("Month To Date")) {
                date = transaction.getDateInput().getMonthValue();
                date2 = now.getMonthValue();
                year = transaction.getDateInput().getYear();
                year2 = now.getYear();
                // Compare it? date == date2
                if (date == date2 && year == year2) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Previous Month")) {
                date = transaction.getDateInput().getMonthValue();
                date2 = now.minusMonths(1).getMonthValue();
                year = transaction.getDateInput().getYear();
                year2 = now.getYear();
                // Compare it? date == date2
                if (date == date2 && year == year2) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Year To Date")) {
                date = transaction.getDateInput().getYear();
                date2 = now.getYear();
                if (date == date2) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Previous Year")) {
                date = transaction.getDateInput().getYear();
                date2 = now.minusYears(1).getYear();
                // Compare it? date == date2
                if (date == date2) {
                    printTransaction(transaction);
                }
            } else {
                if (transaction.inputVendor().toLowerCase().contains(vendor)) {
                    printTransaction(transaction);
                }
            }
        }
    }

    public static void customSearch() {
        // Custom Search
        System.out.println("Custom Search: ");
        LocalDate startDate = Utils.getDateCustom("Enter a start date in (YYYY-MM-DD) or leave blank");
        LocalDate endDate = Utils.getDateCustom("Enter a end date in (YYYY-MM-DD) or leave blank");
        String description = Utils.getStringInput("Enter a description: ").toLowerCase();
        String vendor = Utils.getStringInput("Enter a vendor: ").toLowerCase();
        String amountString = Utils.getStringInput("Enter an amount: "); // Evaluate input to String for isEmpty
        double amount = amountString.isEmpty() ? 0 : Double.parseDouble(amountString); // Evaluate if amount is Empty.

        boolean ifFound = false; // Evaluate if there will be a result

        System.out.println("Here is your custom search: ");
        for (Transaction transaction : transactions
        ) {
            boolean isStartDate = startDate == null || !transaction.getDateInput().isBefore(startDate); // Evaluates transaction date is not before the startDate or if date is null
            boolean isEndDate = endDate == null || !transaction.getDateInput().isAfter(endDate); // Evaluates transactions date is not after the endDate or if date is null
            boolean isDescription = transaction.getDescription().toLowerCase().contains(description);
            boolean isVendor = transaction.getVendor().toLowerCase().contains(vendor);
            boolean isAmount = transaction.getAmount() == amount || amount == 0;
            if (isStartDate && isEndDate && isDescription && isVendor && isAmount) {
                printTransaction(transaction);
                ifFound = true;
            }
        }
        if(!ifFound){
            System.out.println("We couldn't find that. Sorry.");
        }
    }

    private static void printTransaction(Transaction item) {
        System.out.printf("%-15s %-15s %-30s %-25s %15.2f\n",
                item.getDateInput(),
                item.getTimeInput(),
                item.getDescription(),
                item.getVendor(),
                item.getAmount()
        );
    }
}
