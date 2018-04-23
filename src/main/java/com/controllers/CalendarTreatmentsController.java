package com.controllers;

import com.dto.CalendarDTO;
import com.dto.LoginDTO;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CalendarTreatmentsController {
    @RequestMapping(value = "/getTreatmentsSchedule", method = RequestMethod.GET)
    public JSONObject getTreatments() {

        List<CalendarDTO> calendarList = new ArrayList<CalendarDTO>();
        CalendarDTO cal1 = new CalendarDTO();
        cal1.setId(1);
        cal1.setTitle("myTest");


        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ft.setTimeZone(TimeZone.getTimeZone("UTC"));
        String input = "2018-04-24 12:00:00";
        String input2 = "2018-04-24 13:00:00";

        Date t = new Date();
        Date t2 = new Date();
        try {
            t = ft.parse(input);
            t2 = ft.parse(input2);;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal1.setStart(t);
        System.out.println(t);
        cal1.setEnd(t2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","1");
        jsonObject.put("title","test111");
        jsonObject.put("start",t);
        jsonObject.put("end",t2);
        //calendarList.add(cal1);
        return jsonObject;
    }
}
