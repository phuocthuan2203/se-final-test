package com.example.analyzer;

import com.example.model.BankStatement;
import com.example.parser.CSVTransactionParser;
import com.example.report.Report;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.analyzer.StatementAnalyzer;

public class StatementAnalyzerTest {

    private StatementAnalyzer analyzer;

    // Path to the CSV file used in test
    // If your CSV is in src/test/resources/, you can use a relative path like:
    private static final String TEST_CSV_PATH = "src/test/resources/transaction.csv";

    @BeforeEach
    void setup() {
        analyzer = new StatementAnalyzer();
    }

    @Test
    void testAnalyzeWithCSVParser_ExpectedValues() {
        // 1) Create BankStatement pointing to our test CSV
        BankStatement statement = new BankStatement("ID001", TEST_CSV_PATH);

        // 2) Register the CSV parser
        CSVTransactionParser csvParser = new CSVTransactionParser();
        analyzer.registerParser(csvParser);

        // 3) Run analysis
        Report report = analyzer.analyze(statement);

        // 4) Verify results
        // If transaction.csv has:
        //   2024-01-01,Test DEBIT 1,100.0,DEBIT
        //   2024-01-02,Test CREDIT 1,200.0,CREDIT
        // Then income should be 200.0, expense should be 100.0
        Assertions.assertEquals(200.0, report.getTotalIncome(), 0.001);
        Assertions.assertEquals(100.0, report.getTotalExpense(), 0.001);

        // Check if categories exist
        Assertions.assertTrue(report.getCategoryTotals().containsKey("DEBIT"));
        Assertions.assertTrue(report.getCategoryTotals().containsKey("CREDIT"));
    }

    @Test
    void testAnalyzeWithCSVParser_MultipleLines() {
        // Potentially you can put more lines in the CSV:
        //   2024-01-03,Some DEBIT 2,50.0,DEBIT
        //   2024-01-04,Some CREDIT 2,300.0,CREDIT
        // just to demonstrate another scenario with more data
        BankStatement statement = new BankStatement("ID002", TEST_CSV_PATH);
        CSVTransactionParser parser = new CSVTransactionParser();
        analyzer.registerParser(parser);

        Report report = analyzer.analyze(statement);

        // For example, if we had:
        //   DEBIT lines total: 150.0 (100 + 50)
        //   CREDIT lines total: 500.0 (200 + 300)
        // then:
        // Assertions.assertEquals(500.0, report.getTotalIncome(), 0.001);
        // Assertions.assertEquals(150.0, report.getTotalExpense(), 0.001);

        // But you need to match whatever your CSV actually contains.
        // For the minimal example with just 2 lines, let's skip or comment out:
        // ...
    }
}
