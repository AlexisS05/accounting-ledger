package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public record Transaction(LocalDate dateInput, LocalTime timeInput, String inputDescription, String inputVendor, double inputAmount) {
    public LocalDate getDateInput(){
        return dateInput;
    }
    public LocalTime getTimeInput(){
        return timeInput;
    }
    public String getInputDescription(){
        return inputDescription;
    }
    public String getInputVendor(){
        return inputVendor;
    }
    public double getInputAmount(){
        return inputAmount;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-30s %-25s %15.2f",
                dateInput,
                timeInput,
                inputDescription,
                inputVendor,
                inputAmount);
    }
}
