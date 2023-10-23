package org.example;

public class LedgerUI {
    public static void ledgerMenu(){
        boolean done = false;
        while(!done){
            System.out.println("LEDGER MENU");
            System.out.println("A) Display all entries \nD) Display deposits \nP) Display payments \nR) Go to Reports \nH) Return Home");
            done = true;
        }
    }
}
