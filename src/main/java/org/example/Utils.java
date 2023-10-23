package org.example;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    public static char getCharInput() {
        String userInput = scanner.nextLine();
        return userInput.toUpperCase().charAt(0);
    }

    public static String getStringPrompt(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static LocalDate getDate(String prompt){
        while (true){
            System.out.println(prompt);
            String userInput = scanner.nextLine();

            if (userInput.isEmpty()) {
                return LocalDate.now();
            }

            if(!userInput.contains("-")){
                System.out.println("Please try again. It's incorrect");
            } else {
                try {
                    return LocalDate.parse(userInput);
                } catch (DateTimeException e){
                    System.out.println("Invalid date format. Please try again");
                }
            }
        }
    }

    public static LocalTime getTime(String prompt){
        while (true){
            System.out.println(prompt);
            String userInput = scanner.nextLine();

            if (userInput.isEmpty()) {
                return LocalTime.now();
            }

            if(!userInput.contains(":")){
                System.out.println("Please try again. It's incorrect");
            } else {
                try {
                    return LocalTime.parse(userInput);
                } catch (Exception e ){
                    System.out.println("Invalid date format. Please try again");
                }
            }
        }
    }
}
