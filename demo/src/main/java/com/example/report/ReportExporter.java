package com.example.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReportExporter {

    /**
     * Export the report to a CSV file.
     *
     * @param report The Report object containing totals and categories
     * @param outputFilePath Where to write the CSV file (e.g. "outputReport.csv")
     */
    public void exportToCSV(Report report, String outputFilePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Write header
            bw.write("Total Income,Total Expense\n");
            
            // Write the summary line
            bw.write(report.getTotalIncome() + "," + report.getTotalExpense());
            bw.newLine();

            // Write a line for each category total
            bw.write("Category,Amount\n");
            for (Map.Entry<String, Double> entry : report.getCategoryTotals().entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }

            System.out.println("Report successfully written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
