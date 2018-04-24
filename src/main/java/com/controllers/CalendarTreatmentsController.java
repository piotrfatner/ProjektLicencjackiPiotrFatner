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
        /*List<CalendarDTO> calendarList = new ArrayList<CalendarDTO>();
        CalendarDTO cal1 = new CalendarDTO();
        cal1.setId(1);
        cal1.setTitle("myTest");


        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ft.setTimeZone(TimeZone.getTimeZone("UTC"));
        String input = "2018-04-24 12:00:00";
        String input2 = "2018-04-24 13:00:00";
        String input3 = "2018-04-24 15:00:00";
        String input4 = "2018-04-24 16:00:00";

        Date t = new Date();
        Date t2 = new Date();
        Date t3 = new Date();
        Date t4 = new Date();
        try {
            t = ft.parse(input);
            t2 = ft.parse(input2);;
            t3 = ft.parse(input3);;
            t4 = ft.parse(input4);;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal1.setStart(t);
        System.out.println(t);
        cal1.setEnd(t2);

        CalendarDTO cal2 = new CalendarDTO();
        cal2.setId(2);
        cal2.setTitle("myTest22");
        cal2.setStart(t3);
        cal2.setEnd(t4);

        *//*JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","1");
        jsonObject.put("title","test111");
        jsonObject.put("start",t);
        jsonObject.put("end",t2);*//*
        //calendarList.add(cal1);
        calendarList.add(cal1);
        calendarList.add(cal2);
        return calendarList;*/
    }

    @RequestMapping(value="/postVisit", method = RequestMethod.POST)
    public ResponseEntity<?> postVisit(@RequestBody CalendarDTO calendar){
        System.out.println(parseDate(calendar.getStart()));
        return new ResponseEntity(HttpStatus.OK);
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
