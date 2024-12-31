package com.example;

import com.example.analyzer.StatementAnalyzer;
import com.example.model.BankStatement;
import com.example.parser.CSVTransactionParser;
import com.example.report.Report;
import com.example.report.ReportView;
import com.example.report.ReportExporter;

public class MainApplication {
    public static void main(String[] args) {
        // 1) Path to CSV input
        // If you're running from project root, this might be:
        //   "src/main/resources/transaction.csv"
        String inputCSVPath = "src/main/resources/transaction.csv";
        
        // 2) Create a BankStatement pointing to that file
        BankStatement statement = new BankStatement("ID001", inputCSVPath);
        
        // 3) Create an analyzer and register the CSV parser
        StatementAnalyzer analyzer = new StatementAnalyzer();
        analyzer.registerParser(new CSVTransactionParser()); 
        
        // 4) Analyze the statement
        Report report = analyzer.analyze(statement);

        // 5) Display the result on console
        ReportView reportView = new ReportView();
        reportView.display(report);

        // 6) Save report to a file
        ReportExporter exporter = new ReportExporter();
        String outputPath = "outputReport.csv"; // or "build/outputReport.csv", etc.
        exporter.exportToCSV(report, outputPath);
    }
}
