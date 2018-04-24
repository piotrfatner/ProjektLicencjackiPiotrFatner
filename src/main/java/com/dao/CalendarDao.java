package com.dao;

import com.dto.CalendarDTO;
import com.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalendarDao {
    @Autowired
    private JdbcTemplate template;

    public List<CalendarDTO> getCalendarTreatmentsForDoctor(long doctorId){
        String sql = "select RESERVED_TREATMENT_ID, TREATMENT_DATE from tbl_reserved_treatment where DOCTOR_ID_FK = ?";
        return template.query(sql,new Object[] {doctorId},new CalendarMapper());
    }
}
