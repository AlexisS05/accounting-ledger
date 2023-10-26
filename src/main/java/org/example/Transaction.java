package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public record Transaction(LocalDate dateInput, LocalTime timeInput, String inputDescription, String inputVendor, double inputAmount) {
    public LocalDate getDate(){
        return dateInput;
    }
    public LocalTime getTime(){
        return timeInput;
    }
    public String getDescription(){
        return inputDescription;
    }
    public String getVendor(){
        return inputVendor;
    }
    public double getAmount(){
        return inputAmount;
    }

}
