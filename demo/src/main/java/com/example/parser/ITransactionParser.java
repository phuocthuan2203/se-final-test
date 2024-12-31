package com.example.parser;

import com.example.model.Transaction;
import java.util.List;

public interface ITransactionParser {
    List<Transaction> parse(String filePath);
}
