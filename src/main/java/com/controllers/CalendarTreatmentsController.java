package com.controllers;

import com.dto.CalendarDTO;
import com.dto.DoctorDTO;
import com.dto.TreatmentDTO;
import com.services.CalendarTreatmentsService;
import com.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CalendarTreatmentsController {
    @Autowired
    CalendarTreatmentsService calendarTreatmentsService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/getTreatmentsSchedule/{doctorId}/{token}", method = RequestMethod.GET)
    public List<CalendarDTO> getTreatments(@PathVariable("doctorId") long doctorId,@PathVariable("token") String token) {
        if(loginService.checkAuth(token)) {
            return calendarTreatmentsService.getCalendarTreatmentsForDoctor(doctorId);
        }
        return null;
    }

    @RequestMapping(value="/postVisit", method = RequestMethod.POST)
    public ResponseEntity<?> postVisit(@RequestBody TreatmentDTO newReservedTreatment){
        if(loginService.checkAuth(newReservedTreatment.getToken())) {
            return calendarTreatmentsService.insertNewVisit(newReservedTreatment);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/getTreatmentsForUser/{userId}/{token}", method = RequestMethod.GET)
    public List<TreatmentDTO> getTreatmentsForUser(@PathVariable("userId") long userId, @PathVariable("token") String token) {
        if(loginService.checkAuth(token)){
            return calendarTreatmentsService.getCalendarTreatmentsForUser(userId);
        }
        return null;
    }

    @RequestMapping(value = "/getTreatmentsHistoryForUser/{userId}/{token}", method = RequestMethod.GET)
    public List<TreatmentDTO> getTreatmentsHistoryForUser(@PathVariable("userId") long userId, @PathVariable("token") String token) {
        if(loginService.checkAuth(token)){
            return calendarTreatmentsService.getCalendarTreatmentsHistoryForUser(userId);
        }
        return null;
    }

    @RequestMapping(value = "/getAvailableDoctors/{treatmentId}/{token}", method = RequestMethod.GET)
    public List<DoctorDTO> getAvailableDoctors(@PathVariable("treatmentId") long treatmentId, @PathVariable("token") String token) {
        if(loginService.checkAuth(token)){
            return calendarTreatmentsService.getAvailableDoctors(treatmentId);
        }
        return null;
    }


    @RequestMapping(value = "/getTreatment/{treatmentId}/{token}", method = RequestMethod.GET)
    public TreatmentDTO getTreatment(@PathVariable("treatmentId") long treatmentId, @PathVariable("token") String token) {
        if(loginService.checkAuth(token)){
            return calendarTreatmentsService.getTreatment(treatmentId);
        }
        return null;
    }
}
