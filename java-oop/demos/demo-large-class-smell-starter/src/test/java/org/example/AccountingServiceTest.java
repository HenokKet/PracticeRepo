package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountingServiceTest {
    AccountingService accountingService = new AccountingService();
    Visit samplePatientVisit;
    Visit[] visitList;

    @BeforeEach
    void setUp() {
        samplePatientVisit = TestData.getExamplePatientVisit();
        visitList = TestData.getPatientVisits();
    }

    @Test
    void calculateTotalOutstandingPayments() {
        BigDecimal expected = BigDecimal.valueOf(30.00);
        assertEquals(expected, accountingService.calculateTotalOutstandingPayments(TestData.getPatientVisits()));
    }

    @Test
    void calculateTotalReceived() {
        BigDecimal expected = BigDecimal.valueOf(60.00);
        assertEquals(expected, accountingService.calculateTotalReceived(TestData.getPatientVisits()));
    }

}
