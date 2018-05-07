package com.controllers;

import com.dto.TreatmentDTO;
import com.dto.UserDTO;
import com.services.LoginService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/getTreatmentsForUser/{userId}/{token}", method = RequestMethod.GET)
    public List<TreatmentDTO> getTreatmentsForUser(@PathVariable("userId") long userId, @PathVariable("token") String token) {
        if (loginService.checkAuth(token)) {
            return userService.getCalendarTreatmentsForUser(userId);
        }
        return null;
    }

    @RequestMapping(value = "/getTreatmentsHistoryForUser/{userId}/{token}", method = RequestMethod.GET)
    public List<TreatmentDTO> getTreatmentsHistoryForUser(@PathVariable("userId") long userId, @PathVariable("token") String token) {
        if (loginService.checkAuth(token)) {
            return userService.getCalendarTreatmentsHistoryForUser(userId);
        }
        return null;
    }

    @RequestMapping(value = "/cancelReservedTreatment/{reservedTreatmentIdToCancel}", method = RequestMethod.POST)
    public ResponseEntity<?> cancelReservedTreatment(@PathVariable("reservedTreatmentIdToCancel") long reservedTreatmentIdToCancel,
                                                     @RequestBody UserDTO cancelTreatmentRequest) {
        if (loginService.checkAuth(cancelTreatmentRequest.getToken())) {
            return userService.cancelReservedTreatment(reservedTreatmentIdToCancel);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}
