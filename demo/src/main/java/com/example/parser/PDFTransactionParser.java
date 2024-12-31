package com.example.parser;

import com.example.model.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PDFTransactionParser implements ITransactionParser {
    @Override
    public List<Transaction> parse(String filePath) {
        // Stub logic: In a real scenario, you'd parse a PDF and build transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.now(), "Stub PDF Txn 1", 300.0, "DEBIT"));
        return transactions;
    }
}
