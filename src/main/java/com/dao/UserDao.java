package com.dao;

import com.dto.TreatmentDTO;
import com.mapper.TreatmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    @Autowired
    JdbcTemplate template;

    public int cancelReservedTreatment(long reservedTreatmentIdToCancel) {
        String sql = "DELETE FROM tbl_reserved_treatment WHERE RESERVED_TREATMENT_ID = ?;";
        return template.update(sql,
                new Object[]{reservedTreatmentIdToCancel});
    }

    public List<TreatmentDTO> getCalendarTreatmentsForUser(long userId) {
        String sql = "SELECT rt.RESERVED_TREATMENT_ID,rt.TREATMENT_DATE, rt.NOTES, t.NAME " +
                "from tbl_reserved_treatment rt " +
                "JOIN tbl_treatment t ON rt.TREATMENT_ID_FK = t.TREATMENT_ID " +
                "where rt.USER_ID_FK = ? and rt.TREATMENT_DATE > now() " +
                "order by rt.TREATMENT_DATE";
        return template.query(sql, new Object[]{userId}, new TreatmentMapper());
    }

    public List<TreatmentDTO> getCalendarTreatmentsHistoryForUser(long userId) {
        String sql = "SELECT rt.RESERVED_TREATMENT_ID,rt.TREATMENT_DATE, rt.NOTES, t.NAME " +
                "from tbl_reserved_treatment rt " +
                "JOIN tbl_treatment t ON rt.TREATMENT_ID_FK = t.TREATMENT_ID " +
                "where rt.USER_ID_FK = ? and rt.TREATMENT_DATE < now() " +
                "order by rt.TREATMENT_DATE DESC";
        return template.query(sql, new Object[]{userId}, new TreatmentMapper());
    }
}
