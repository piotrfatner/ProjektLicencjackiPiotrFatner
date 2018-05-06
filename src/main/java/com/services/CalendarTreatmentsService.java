package com.services;

import com.dao.CalendarDao;
import com.dto.CalendarDTO;
import com.dto.DoctorDTO;
import com.dto.TreatmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarTreatmentsService {
    @Autowired
    CalendarDao calendarDao;

    public List<CalendarDTO> getCalendarTreatmentsForDoctor(long doctorId){
        return calendarDao.getCalendarTreatmentsForDoctor(doctorId);
    }

    public ResponseEntity<?> insertNewVisit(TreatmentDTO treatmentDTO){
        if(calendarDao.insertNewTreatment(treatmentDTO) != 1){
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    public List<TreatmentDTO> getCalendarTreatmentsForUser(long userId){
        return calendarDao.getCalendarTreatmentsForUser(userId);
    }

    public List<TreatmentDTO> getCalendarTreatmentsHistoryForUser(long userId){
        return calendarDao.getCalendarTreatmentsHistoryForUser(userId);
    }

    public List<DoctorDTO> getAvailableDoctors(long treatmentId){
        return calendarDao.getAvailableDoctors(treatmentId);
    }

    public TreatmentDTO getTreatment(long userId){
        return calendarDao.getTreatment(userId);
    }
}
