package com.mapper;

import com.dto.CalendarDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class CalendarMapper implements RowMapper<CalendarDTO> {
    @Override
    public CalendarDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        CalendarDTO calendarDTO= new CalendarDTO();
        calendarDTO.setId(resultSet.getLong("RESERVED_TREATMENT_ID"));
        calendarDTO.setStart(resultSet.getTimestamp("TREATMENT_DATE"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendarDTO.getStart());
        cal.add(Calendar.HOUR_OF_DAY,1);
        calendarDTO.setEnd(cal.getTime());
        calendarDTO.setTitle("Termin Zajęty");
        return calendarDTO;
    }
}
