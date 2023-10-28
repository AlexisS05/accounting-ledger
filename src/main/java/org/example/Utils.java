package org.example;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);
    public static char getCharInput() {
        String userInput = scanner.nextLine().toUpperCase().trim();
        if (!userInput.isEmpty()) {
            return userInput.charAt(0);
        } else {
            return getCharInput();
        }
    }

    public static String getStringInputCustom(String prompt) { // For the amount entry
        String input;
        System.out.println(prompt);
        input = scanner.nextLine();

        // Prompts the user in custom search in case input is empty or non-numeric
        while (!input.isEmpty() && !isNumeric(input)) {
            System.out.println("Invalid input. Please enter a valid value: ");
            input = scanner.nextLine();
        }

        return input;
    }

    public static boolean isNumeric(String str) { // To evaluate in getStringInputCustom
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getStringInput(String prompt) {
        String input;
        System.out.println(prompt);
        input = scanner.nextLine();

        // Prompts the user to enter only words and not numbers
        while (!input.matches("^[a-zA-Z]+$")) {
            System.out.println("Invalid input. Please enter a input: ");
            input = scanner.nextLine();
        }

        return input;
    }

    public static double getDoubleInput(String amountString) { // For main
        while (true) {
            if (amountString.isEmpty()) {
                System.out.println("Amount cannot be empty. Please enter a valid numeric amount.");
                amountString = Utils.getStringInputCustom("Enter an amount: ");
            } else {
                try {
                    return Double.parseDouble(amountString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric amount.");
                    amountString = Utils.getStringInputCustom("Enter an amount: ");
                }
            }
        }
    }

    public static double parseAmount(String amountString) { // Parse the amount from getStringInputCustom
        if (amountString.isEmpty()) {
            return 0;
        } else {
            try {
                return Double.parseDouble(amountString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                return parseAmount(Utils.getStringInputCustom("Enter an amount: "));
            }
        }
    }


    public static LocalDate getDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                return LocalDate.now();
            }

            try {
                LocalDate parsedDate = LocalDate.parse(userInput);
                LocalDate currentDate = LocalDate.now();

                if (parsedDate.isAfter(currentDate)) {
                    System.out.println("You entered a date in the future. Please enter a valid date.");
                } else {
                    return parsedDate;
                }
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    public static LocalDate getDateCustom(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                return null;
            }

            try {
                return LocalDate.parse(userInput);
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static LocalTime getTime(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                LocalTime now = LocalTime.now();
                String newNow = now.format(format);
                return LocalTime.parse(newNow, format);
            }

            try {
                return LocalTime.parse(userInput, format);
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please try again");
            }
        }
    }

    public static BufferedWriter createDefaultCSV(String fileName) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("CSV file created!");
            }
            // Append data to the existing file or create a new one if it doesn't exist
            FileWriter fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("Error creating or opening the CSV file!");
        }
        return bufferedWriter;
    }
}
