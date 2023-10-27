package org.example;

import java.time.LocalDate;

import static org.example.LedgerUI.*;

public class Reports {
    public static void reportsMenu() {
        while (true) {
            System.out.print("\u001B[31m");
            System.out.printf("%20s\n", "REPORTS MENU");
            System.out.print("\u001B[0m");
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

        printTransactionTitle();

        for (Transaction transaction : transactions) {
            if (filter.equals("Month To Date")) {
                date = transaction.getDate().getMonthValue();
                date2 = now.getMonthValue();
                year = transaction.getDate().getYear();
                year2 = now.getYear();
                // Compare it? date == date2
                if (date == date2 && year == year2) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Previous Month")) {
                date = transaction.getDate().getMonthValue();
                date2 = now.minusMonths(1).getMonthValue();
                year = transaction.getDate().getYear();
                year2 = now.getYear();
                // Compare it? date == date2
                if (date == date2 && year == year2) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Year To Date")) {
                date = transaction.getDate().getYear();
                date2 = now.getYear();

                if (date == date2) {
                    printTransaction(transaction);
                }
            } else if (filter.equals("Previous Year")) {
                date = transaction.getDate().getYear();
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


        System.out.print("\u001B[31m");
        System.out.printf("%40s\n", "CUSTOM SEARCH RESULT");
        System.out.print("\u001B[0m");
        printTransactionTitle();

        boolean ifFound = false; // Evaluate if there will be a result

        for (Transaction transaction : transactions
        ) {
            boolean isStartDate = startDate == null || !transaction.getDate().isBefore(startDate); // Evaluates transaction date is not before the startDate or if date is null
            boolean isEndDate = endDate == null || !transaction.getDate().isAfter(endDate); // Evaluates transactions date is not after the endDate or if date is null
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
}
