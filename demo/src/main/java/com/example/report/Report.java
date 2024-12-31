package com.example.report;

import java.util.HashMap;
import java.util.Map;

public class Report {
    private double totalIncome;
    private double totalExpense;
    private Map<String, Double> categoryTotals;

    public Report() {
        this.categoryTotals = new HashMap<>();
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Map<String, Double> getCategoryTotals() {
        return categoryTotals;
    }

    public void setCategoryTotals(Map<String, Double> categoryTotals) {
        this.categoryTotals = categoryTotals;
    }
}
