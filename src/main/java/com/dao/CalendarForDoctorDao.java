package com.dao;

import com.dto.CalendarDTO;
import com.dto.CalendarForDoctorDTO;
import com.mapper.CalendarForDoctorMapper;
import com.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalendarForDoctorDao {
    @Autowired
    private JdbcTemplate template;

    public List<CalendarForDoctorDTO> getReservedTreatmentsScheduleForDoctor(long doctorUserId) {
        String sql = "select RESERVED_TREATMENT_ID,TREATMENT_DATE, NOTES, t.NAME, u.FIRSTNAME, u.LASTNAME " +
                "from tbl_reserved_treatment rt " +
                "JOIN tbl_treatment t ON rt.TREATMENT_ID_FK = t.TREATMENT_ID " +
                "JOIN tbl_doctor d ON d.DOCTOR_ID = rt.DOCTOR_ID_FK " +
                "JOIN tbl_user u ON u.USER_ID = rt.USER_ID_FK " +
                "WHERE d.USER_ID_FK = ?";
        return template.query(sql, new Object[]{doctorUserId}, new CalendarForDoctorMapper());
    }
}
