package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitFormatterTest {
    VisitFormatter visitFormatter = new VisitFormatter();
    Visit samplePatientVisit;
    Visit[] visitList;

    @BeforeEach
    void setUp() {
        samplePatientVisit = TestData.getExamplePatientVisit();
        visitList = TestData.getPatientVisits();
    }


    @Test
    void formatDataTime() {
        LocalDateTime in = LocalDateTime.of(1976, 1, 29, 10, 28);
        String expected = "01/29/1976 10:28";

        assertEquals(expected, visitFormatter.formatDateTime(in));

    }

    @Test
    void formatPatientForListOutput() {
        String actual = visitFormatter.formatPatientForListOutput(samplePatientVisit);
        String expected = "01/01/2022 11:15 - McCandles, Jacob for Dr Kilroy - 30.0: PAID";
        assertEquals(expected, actual);
    }
}
