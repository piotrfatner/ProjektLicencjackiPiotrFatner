package com.mapper;

import com.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDTO> {
    @Override
    public UserDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(resultSet.getLong("USER_ID"));
        userDTO.setUsername(resultSet.getString("USERNAME"));
        userDTO.setEmail(resultSet.getString("EMAIL"));
        userDTO.setToken(resultSet.getString("TOKEN"));
        return userDTO;
    }
}
