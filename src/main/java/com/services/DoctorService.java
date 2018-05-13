package com.services;

import com.dao.CalendarForDoctorDao;
import com.dto.CalendarForDoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    CalendarForDoctorDao calendarForDoctorDao;

    public List<CalendarForDoctorDTO> getReservedTreatmentsScheduleForDoctor(long doctorUserId){
        return calendarForDoctorDao.getReservedTreatmentsScheduleForDoctor(doctorUserId);
    }
}
