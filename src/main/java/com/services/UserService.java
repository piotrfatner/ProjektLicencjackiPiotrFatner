package com.services;

import com.dao.UserDao;
import com.dto.TreatmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public ResponseEntity<?> cancelReservedTreatment(long reservedTreatmentIdToCancel){
        userDao.cancelReservedTreatment(reservedTreatmentIdToCancel);
        return new ResponseEntity(HttpStatus.OK);
    }

    public List<TreatmentDTO> getCalendarTreatmentsForUser(long userId){
        return userDao.getCalendarTreatmentsForUser(userId);
    }

    public List<TreatmentDTO> getCalendarTreatmentsHistoryForUser(long userId){
        return userDao.getCalendarTreatmentsHistoryForUser(userId);
    }
}
