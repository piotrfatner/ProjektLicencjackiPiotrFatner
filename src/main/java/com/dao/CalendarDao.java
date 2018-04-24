package com.dao;

import com.dto.CalendarDTO;
import com.dto.TreatmentDTO;
import com.mapper.CalendarMapper;
import com.mapper.TreatmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalendarDao {
    @Autowired
    private JdbcTemplate template;

    public List<CalendarDTO> getCalendarTreatmentsForDoctor(long doctorId) {
        String sql = "select RESERVED_TREATMENT_ID, TREATMENT_DATE from tbl_reserved_treatment where DOCTOR_ID_FK = ?";
        return template.query(sql, new Object[]{doctorId}, new CalendarMapper());
    }

    public int insertNewTreatment(TreatmentDTO treatmentDTO) {
        String sql = "INSERT INTO tbl_reserved_treatment(TREATMENT_DATE,NOTES,DOCTOR_ID_FK,TREATMENT_ID_FK,USER_ID_FK) VALUES(?,?,?,?,?);";
        return template.update(sql,
                new Object[]{treatmentDTO.getTreatmentDate(), treatmentDTO.getNotes(), treatmentDTO.getDoctorId(), treatmentDTO.getTreatmentId(), treatmentDTO.getUserId()});
        //return 1;
    }

    public List<TreatmentDTO> getCalendarTreatmentsForUser(long userId) {
        String sql = "SELECT rt.TREATMENT_DATE, rt.NOTES, t.NAME " +
                "from tbl_reserved_treatment rt " +
                "JOIN tbl_treatment t ON rt.TREATMENT_ID_FK = t.TREATMENT_ID " +
                "where rt.USER_ID_FK = ?";
        return template.query(sql, new Object[]{userId}, new TreatmentMapper());
    }
}
