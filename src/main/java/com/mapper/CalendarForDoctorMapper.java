package com.mapper;

import com.dto.CalendarForDoctorDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class CalendarForDoctorMapper implements RowMapper<CalendarForDoctorDTO> {
    @Override
    public CalendarForDoctorDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        CalendarForDoctorDTO calendarForDoctorDTO = new CalendarForDoctorDTO();
        calendarForDoctorDTO.setId(resultSet.getLong("RESERVED_TREATMENT_ID"));
        calendarForDoctorDTO.setStart(resultSet.getTimestamp("TREATMENT_DATE"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendarForDoctorDTO.getStart());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        calendarForDoctorDTO.setEnd(cal.getTime());
        calendarForDoctorDTO.setTitle("Zabieg: " + resultSet.getString("NAME") + "\nPacjent: " +
                resultSet.getString("FIRSTNAME") + " "
                + resultSet.getString("LASTNAME") + "\nInformacje od pacjenta: "+resultSet.getString("NOTES"));
        return calendarForDoctorDTO;
    }
}
