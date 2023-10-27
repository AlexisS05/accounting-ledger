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

    public static String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextDouble()) {
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
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
