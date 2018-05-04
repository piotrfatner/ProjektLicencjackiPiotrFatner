package com.mapper;

import com.dto.DoctorDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<DoctorDTO> {
    @Override
    public DoctorDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorId(resultSet.getLong("DOCTOR_ID"));
        doctorDTO.setFirstName(resultSet.getString("FIRSTNAME"));
        doctorDTO.setLastName(resultSet.getString("LASTNAME"));
        doctorDTO.setSpecialization(resultSet.getString("SPECIALIZATION"));
        return doctorDTO;
    }
}
