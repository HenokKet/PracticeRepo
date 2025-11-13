package org.example.models;

import java.time.LocalDateTime;

public class Medication {
    private String MedicationName;
    private int ApplicationNo;
    private int UserId;
    private int Qty;
    private LocalDateTime firstDose;
    private LocalDateTime lastDose;
    private int doseIntervalHours;

    public Medication(){

    }
    public Medication(String medicationName, int applicationNo, int qty, int userId, LocalDateTime firstDose, LocalDateTime lastDose, int doseIntervalHours) {
        MedicationName = medicationName;
        ApplicationNo = applicationNo;
        Qty = qty;
        UserId = userId;
        this.firstDose = firstDose;
        this.lastDose = lastDose;
        this.doseIntervalHours = doseIntervalHours;
    }

    public String getMedicationName() {
        return MedicationName;
    }

    public void setMedicationName(String medicationName) {
        MedicationName = medicationName;
    }

    public int getUserId() {
        return UserId;
    }
    public void setUserId(int userId) {
        UserId = userId;
    }
    public int getApplicationNo() {
        return ApplicationNo;
    }

    public void setApplicationNo(int applicationNo) {
        ApplicationNo = applicationNo;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public LocalDateTime getFirstDose() {
        return firstDose;
    }

    public void setFirstDose(LocalDateTime firstDose) {
        this.firstDose = firstDose;
    }

    public LocalDateTime getLastDose() {
        return lastDose;
    }

    public void setLastDose(LocalDateTime lastDose) {
        this.lastDose = lastDose;
    }

    public int getDoseIntervalHours() {
        return doseIntervalHours;
    }

    public void setDoseIntervalHours(int doseIntervalHours) {
        this.doseIntervalHours = doseIntervalHours;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "MedicationName='" + MedicationName + '\'' +
                ", ApplicationNo=" + ApplicationNo +
//                ", UserId="+ UserId +
                ", Qty=" + Qty +
                ", firstDose=" + firstDose +
                ", lastDose=" + lastDose +
                ", doseIntervalHours=" + doseIntervalHours +
                '}';
    }
}
