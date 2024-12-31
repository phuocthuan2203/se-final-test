package com.example.model;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String description;
    private double amount;
    private String type;  // e.g. DEBIT, CREDIT, etc.

    public Transaction(LocalDate date, String description, double amount, String type) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // getters
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return "Transaction{" +
               "date=" + date +
               ", description='" + description + '\'' +
               ", amount=" + amount +
               ", type='" + type + '\'' +
               '}';
    }
}
