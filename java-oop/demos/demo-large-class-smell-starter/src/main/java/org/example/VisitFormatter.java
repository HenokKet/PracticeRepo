package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitFormatter {
    public String formatDateTime(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm"));
    }

    public String formatPatientForListOutput(Visit patientVisit) {
        StringBuilder result = new StringBuilder();
        Patient patient = patientVisit.getPatient();
        Provider provider = patientVisit.getProvider();

        result.append(formatDateTime(patientVisit.getAppointmentTime())).append(" - ");
        result.append(patient.getPatientLastName()).append(", ").append(patient.getPatientFirstName()).append(" ");
        result.append("for ").append(provider.getProviderTitle()).append(" ").append(provider.getProviderLastName()).append(" - ");
        result.append(patientVisit.getVisitCost()). append(": ").append(patientVisit.isCostPaid() ? "PAID" : "DUE");

        return result.toString();
    }
}
