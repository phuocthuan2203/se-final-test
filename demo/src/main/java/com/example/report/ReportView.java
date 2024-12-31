package com.example.report;

public class ReportView {
    public void display(Report report) {
        System.out.println("=== REPORT VIEW ===");
        System.out.println("Total Income: " + report.getTotalIncome());
        System.out.println("Total Expense: " + report.getTotalExpense());
        System.out.println("Category Totals: " + report.getCategoryTotals());
        System.out.println("===================");
    }
}
