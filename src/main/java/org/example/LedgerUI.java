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
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Ledger> transactions = getTransactions();

    public static ArrayList<Ledger> getTransactions(){
        ArrayList<Ledger> transactions = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while((input = bufReader.readLine()) !=null ){
                String[] data = input.split("\\|");

                Ledger ledger = new Ledger(LocalDate.parse(data[0]), LocalTime.parse(data[1]), data[3], data[4], Double.parseDouble(data[5]));
                transactions.add(ledger);
                System.out.println(transactions);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
    public static void ledgerMenu(){
        boolean done = false;
        while(!done){
            System.out.println("LEDGER MENU");
            System.out.println("A) Display all entries \nD) Display deposits \nP) Display payments \nR) Go to Reports \nH) Return Home");
            char input = Utils.getCharInput();
            done = true;
        }
    }
}
