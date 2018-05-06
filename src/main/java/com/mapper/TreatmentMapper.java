package com.mapper;

import com.dto.TreatmentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentMapper implements RowMapper<TreatmentDTO> {
    @Override
    public TreatmentDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TreatmentDTO treatmentDTO = new TreatmentDTO();
        treatmentDTO.setReservedTreatmentId(resultSet.getLong("RESERVED_TREATMENT_ID"));
        treatmentDTO.setTreatmentDate(resultSet.getTimestamp("TREATMENT_DATE"));
        treatmentDTO.setNotes(resultSet.getString("NOTES"));
        treatmentDTO.setTreatmentName(resultSet.getString("NAME"));
        return treatmentDTO;
    }
}
