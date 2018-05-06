package com.dto;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

public class TreatmentDTO implements Serializable {
    private static final long serialVersionUID = 3594504287177758753L;
    private long reservedTreatmentId;
    private Date treatmentDate;
    private String notes;
    private long doctorId;
    private long treatmentId;
    private long userId;
    private String treatmentName;
    private String token;

    public long getReservedTreatmentId() {
        return reservedTreatmentId;
    }

    public void setReservedTreatmentId(long reservedTreatmentId) {
        this.reservedTreatmentId = reservedTreatmentId;
    }

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(Timestamp treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
