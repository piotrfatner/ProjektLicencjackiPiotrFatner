package com.controllers;

import com.dto.CalendarDTO;
import com.dto.LoginDTO;
import com.dto.TreatmentDTO;
import com.services.CalendarTreatmentsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CalendarTreatmentsController {
    @Autowired
    CalendarTreatmentsService calendarTreatmentsService;

    @RequestMapping(value = "/getTreatmentsSchedule/{doctorId}", method = RequestMethod.GET)
    public List<CalendarDTO> getTreatments(@PathVariable("doctorId") long doctorId) {
        return calendarTreatmentsService.getCalendarTreatmentsForDoctor(1);
    }

    @RequestMapping(value="/postVisit", method = RequestMethod.POST)
    public ResponseEntity<?> postVisit(@RequestBody TreatmentDTO newReservedTreatment){
        System.out.println(newReservedTreatment.getTreatmentDate());
        return calendarTreatmentsService.insertNewVisit(newReservedTreatment);
    }

    @RequestMapping(value = "/getTreatmentsForUser/{userId}", method = RequestMethod.GET)
    public List<TreatmentDTO> getTreatmentsForUser(@PathVariable("userId") long userId) {
        return calendarTreatmentsService.getCalendarTreatmentsForUser(userId);
    }

    public Date parseDate(Date date){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ft.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateAfterParsing = ft.format(date);
        Date parsedDate = new Date();
        try {
            parsedDate = ft.parse(dateAfterParsing);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
