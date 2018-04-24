package com.services;

import com.dao.CalendarDao;
import com.dto.CalendarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarTreatmentsService {
    @Autowired
    CalendarDao calendarDao;
    public List<CalendarDTO> getCalendarTreatmentsForDoctor(long doctorId){
        return calendarDao.getCalendarTreatmentsForDoctor(doctorId);
    }
}
