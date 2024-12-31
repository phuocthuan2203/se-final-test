package com.example.analyzer;

import com.example.model.BankStatement;
import com.example.model.Transaction;
import com.example.parser.ITransactionParser;
import com.example.report.Report;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementAnalyzer {
    private List<ITransactionParser> parsers = new ArrayList<>();

    public StatementAnalyzer() { }

    public void registerParser(ITransactionParser parser) {
        parsers.add(parser);
    }

    public Report analyze(BankStatement statement) {
        if (parsers.isEmpty()) {
            throw new IllegalStateException("No parsers registered!");
        }
        
        // For demo: pick the first parser
        ITransactionParser chosenParser = parsers.get(0);

        // Parse file -> add to statement
        List<Transaction> parsedTransactions = chosenParser.parse(statement.getFilePath());
        statement.addTransactions(parsedTransactions);

        // Summarize
        double income = 0.0;
        double expense = 0.0;
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Transaction txn : statement.getTransactions()) {
            if ("CREDIT".equalsIgnoreCase(txn.getType())) {
                income += txn.getAmount();
            } else if ("DEBIT".equalsIgnoreCase(txn.getType())) {
                expense += txn.getAmount();
            }
            // For simplicity, treat "type" as the category
            categoryTotals.merge(txn.getType(), txn.getAmount(), Double::sum);
        }

        // Build final report
        Report report = new Report();
        report.setTotalIncome(income);
        report.setTotalExpense(expense);
        report.setCategoryTotals(categoryTotals);

        return report;
    }
}
