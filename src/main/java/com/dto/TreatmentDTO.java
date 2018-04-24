package com.dto;

import java.io.Serializable;
import java.sql.Date;

public class TreatmentDTO implements Serializable {
    private static final long serialVersionUID = 3594504287177758753L;
    private long id;
    private String title;
    private Date start;
    private Date end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
