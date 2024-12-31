package com.example.model;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class BankStatement {
    private String id;
    private String filePath;
    private List<Transaction> transactions;

    public BankStatement(String id, String filePath) {
        this.id = id;
        this.filePath = filePath;
        this.transactions = new ArrayList<>();
    }

    public String getId() { 
        return id; 
    }

    public String getFilePath() { 
        return filePath; 
    }

    public List<Transaction> getTransactions() { 
        return transactions; 
    }

    public void addTransactions(List<Transaction> transactions) {
        this.transactions.addAll(transactions);
    }

    // Example utility methods:
    public List<Transaction> filterByType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Transaction> filterByDateRange(LocalDate start, LocalDate end) {
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .collect(Collectors.toList());
    }

    public double getTotalAmount() {
        return transactions.stream()
                           .mapToDouble(Transaction::getAmount)
                           .sum();
    }
}
