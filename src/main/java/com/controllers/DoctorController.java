package com.controllers;

import com.dto.CalendarDTO;
import com.dto.CalendarForDoctorDTO;
import com.services.DoctorService;
import com.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/getReservedTreatmentsScheduleForDoctor/{userId}/{token}", method = RequestMethod.GET)
    public List<CalendarForDoctorDTO> getReservedTreatmentsScheduleForDoctor(@PathVariable("userId") long doctorUserId, @PathVariable("token") String token) {
        if(loginService.checkAuthForDoctor(token)) {
            return doctorService.getReservedTreatmentsScheduleForDoctor(doctorUserId);
        }
        return null;
    }
}
