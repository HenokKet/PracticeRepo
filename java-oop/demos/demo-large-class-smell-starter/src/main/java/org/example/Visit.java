package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Visit {
    private Patient patient;
    private Provider provider;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    private String[] providerNotes;
    private String[] relatedPrescriptions;
    private LocalDateTime appointmentTime;
    private BigDecimal visitCost;
    private boolean costPaid;
    private String primaryDiagnosis;
    private String treatmentPlan;



    public BigDecimal getVisitCost() {
        return visitCost;
    }

    public void setVisitCost(BigDecimal visitCost) {
        this.visitCost = visitCost;
    }

    public boolean isCostPaid() {
        return costPaid;
    }

    public void setCostPaid(boolean costPaid) {
        this.costPaid = costPaid;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String[] getProviderNotes() {
        return providerNotes;
    }

    public void setProviderNotes(String[] providerNotes) {
        this.providerNotes = providerNotes;
    }

    public String[] getRelatedPrescriptions() {
        return relatedPrescriptions;
    }

    public void setRelatedPrescriptions(String[] relatedPrescriptions) {
        this.relatedPrescriptions = relatedPrescriptions;
    }


    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
