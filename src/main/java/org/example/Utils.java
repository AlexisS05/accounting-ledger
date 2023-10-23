package org.example;

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
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    public static LocalDate getDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                return LocalDate.now();
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
}
