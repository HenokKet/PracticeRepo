package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestData {
    public static Visit[] getPatientVisits() {
        Visit[] result = new Visit[3];

        Visit visit = new Visit();
        Patient patient = new Patient();

        patient.setPatientId("ABC123");

        patient.setPatientFirstName("Jacob");
        patient.setPatientLastName("McCandles");
        patient.setPatientAddress1("123 Main Street");
        patient.setPatientAddress2("#2");
        patient.setPatientCity("McCandles");
        patient.setPatientState("TX");
        patient.setPatientZip("11111");
        visit.setPatient(patient);
        visit.setAppointmentTime(LocalDateTime.of(2022, 1, 1, 11, 15));
        visit.setVisitCost(BigDecimal.valueOf(30.00));
        visit.setCostPaid(true);
        visit.setPrimaryDiagnosis("Stubbed toe");
        visit.setTreatmentPlan("Walk it off");
        visit.setProviderNotes(new String[2]);
        visit.getProviderNotes()[0] = "Note 1";
        visit.getProviderNotes()[1] = "Note 2";
        visit.setRelatedPrescriptions(new String[1]);
        visit.getRelatedPrescriptions()[0] = "Alleve";
        Provider provider = new Provider();

        provider.setProviderId("ZXY21");
        provider.setProviderFirstName("Pat");
        provider.setProviderLastName("Kilroy");
        provider.setProviderTitle("Dr");
        provider.setProviderSuffix("");
        visit.setProvider(provider);

        result[0] = visit;

        visit = new Visit();

        patient = new Patient();
        patient.setPatientId("ABC123");

        patient.setPatientFirstName("Jacob");
        patient.setPatientLastName("McCandles");
        patient.setPatientAddress1("123 Main Street");
        patient.setPatientAddress2("#2");
        patient.setPatientCity("McCandles");
        patient.setPatientState("TX");
        patient.setPatientZip("11111");
        visit.setPatient(patient);
        visit.setAppointmentTime(LocalDateTime.of(2022, 2, 2, 10, 30));
        visit.setVisitCost(BigDecimal.valueOf(30.00));
        visit.setCostPaid(false);
        visit.setPrimaryDiagnosis("Bruised knee");
        visit.setTreatmentPlan("Stretching");
        visit.setProviderNotes(new String[2]);
        visit.getProviderNotes()[0] = "Note 1";
        visit.getProviderNotes()[1] = "Note 2";
        visit.setRelatedPrescriptions(new String[1]);
        visit.getRelatedPrescriptions()[0] = "Aspirin";
        provider = new Provider();
        provider.setProviderId("NEWID");
        provider.setProviderFirstName("New");
        provider.setProviderLastName("Nurse Practitioner");
        provider.setProviderTitle("NP");
        provider.setProviderSuffix("Jr");
        visit.setProvider(provider);
        result[1] = visit;

        visit = new Visit();
        patient = new Patient();

        patient.setPatientId("NOTJAKE");

        patient.setPatientFirstName("Barbara");
        patient.setPatientLastName("Jones");
        patient.setPatientAddress1("321 Main Street");
        patient.setPatientAddress2("#5");
        patient.setPatientCity("San Francisco");
        patient.setPatientState("CA");
        patient.setPatientZip("90210");
        visit.setPatient(patient);
        visit.setAppointmentTime(LocalDateTime.of(2021, 5, 2, 15, 0));
        visit.setVisitCost(BigDecimal.valueOf(30.00));
        visit.setCostPaid(true);
        visit.setPrimaryDiagnosis("Stubbed toe");
        visit.setTreatmentPlan("Walk it off");
        visit.setProviderNotes(new String[2]);
        visit.getProviderNotes()[0] = "Note 1";
        visit.getProviderNotes()[1] = "Note 2";
        visit.setRelatedPrescriptions(new String[1]);
        visit.getRelatedPrescriptions()[0] = "Alleve";

        provider = new Provider();
        provider.setProviderId("ZXY21");
        provider.setProviderFirstName("Pat");
        provider.setProviderLastName("Kilroy");
        provider.setProviderTitle("Dr");
        provider.setProviderSuffix("");
        visit.setProvider(provider);
        result[2] = visit;

        return result;
    }

    public static Visit getExamplePatientVisit() {
        Visit result = new Visit();
        Patient patient = new Patient();
        Provider provider = new Provider();
        patient.setPatientId("ABC123");

        patient.setPatientFirstName("Jacob");
        patient.setPatientLastName("McCandles");
        patient.setPatientAddress1("123 Main Street");
        patient.setPatientAddress2("#2");
        patient.setPatientCity("McCandles");
        patient.setPatientState("TX");
        patient.setPatientZip("11111");
        result.setPatient(patient);
        result.setAppointmentTime(LocalDateTime.of(2022, 1, 1, 11, 15));
        result.setVisitCost(BigDecimal.valueOf(30.00));
        result.setCostPaid(true);
        result.setPrimaryDiagnosis("Stubbed toe");
        result.setTreatmentPlan("Walk it off");
        result.setProviderNotes(new String[2]);
        result.getProviderNotes()[0] = "Note 1";
        result.getProviderNotes()[1] = "Note 2";
        result.setRelatedPrescriptions(new String[1]);
        result.getRelatedPrescriptions()[0] = "Alleve";
        provider.setProviderId("ZXY21");
        provider.setProviderFirstName("Pat");
        provider.setProviderLastName("Kilroy");
        provider.setProviderTitle("Dr");
        provider.setProviderSuffix("");
        result.setProvider(provider);
        return result;
    }
}
