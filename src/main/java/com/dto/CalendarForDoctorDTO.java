package com.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CalendarForDoctorDTO implements Serializable{
    private static final long serialVersionUID = 1937205843901840481L;
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

    public void setStart(Timestamp start) {
        this.start = new Date(start.getTime());
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
