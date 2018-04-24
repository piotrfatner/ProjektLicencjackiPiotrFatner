package com.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class TreatmentDTO implements Serializable {
    private static final long serialVersionUID = 3594504287177758753L;
    private Timestamp treatmentDate;
    private String notes;
    private long doctorId;
    private long treatmentId;
    private long userId;

    public Timestamp getTreatmentDate() {
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
}
