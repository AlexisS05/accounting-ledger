package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ledger {
    LocalDate dateInput;
    LocalTime timeInput;
    String inputDescription;
    String inputVendor;
    double inputAmount;

    public Ledger(LocalDate dateInput, LocalTime timeInput, String inputDescription, String inputVendor, double inputAmount) {
        this.dateInput = dateInput;
        this.timeInput = timeInput;
        this.inputDescription = inputDescription;
        this.inputVendor = inputVendor;
        this.inputAmount = inputAmount;
    }

    public LocalDate getDateInput() {
        return dateInput;
    }

    public LocalTime getTimeInput() {
        return timeInput;
    }

    public String getInputDescription() {
        return inputDescription;
    }

    public String getInputVendor() {
        return inputVendor;
    }

    public double getInputAmount() {
        return inputAmount;
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "dateInput=" + dateInput +
                ", timeInput=" + timeInput +
                ", inputDescription='" + inputDescription + '\'' +
                ", inputVendor='" + inputVendor + '\'' +
                ", inputAmount=" + inputAmount +
                '}';
    }
}
