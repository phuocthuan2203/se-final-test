package com.example.parser;

import com.example.model.Transaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVTransactionParser implements ITransactionParser {

    @Override
    public List<Transaction> parse(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Ignore empty or header lines if needed
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Format: yyyy-MM-dd,description,amount,type
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.err.println("Invalid CSV line: " + line);
                    continue;
                }

                LocalDate date = LocalDate.parse(parts[0].trim());
                String description = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                String type = parts[3].trim();  // e.g. DEBIT or CREDIT

                Transaction txn = new Transaction(date, description, amount, type);
                transactions.add(txn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
