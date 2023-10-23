package org.example;

import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    public static char getCharInput() {
        String userInput = scanner.nextLine();
        return userInput.toUpperCase().charAt(0);
    }

    public static String getStringPrompt(Scanner scanner, String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
