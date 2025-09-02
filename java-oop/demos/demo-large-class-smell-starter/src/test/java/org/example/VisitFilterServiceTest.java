package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitFilterServiceTest {
    VisitFilterService visitFilterService = new VisitFilterService();
    Visit samplePatientVisit;
    Visit[] visitList;

    @BeforeEach
    void setUp() {
        samplePatientVisit = TestData.getExamplePatientVisit();
        visitList = TestData.getPatientVisits();
    }
    @Test
    void getVisitsByProviderId() {
        int expectedSize = 2;
        assertEquals(expectedSize, visitFilterService.getVisitsByProviderId("ZXY21", TestData.getPatientVisits()).length);
    }

    @Test
    void getVisitsByPatientId() {
        int expectedSize = 2;
        assertEquals(expectedSize, visitFilterService.getVisitsByPatientId("ABC123", TestData.getPatientVisits()).length);
    }
}
