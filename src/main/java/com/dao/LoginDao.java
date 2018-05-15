package com.dao;

import com.dto.RegisterDTO;
import com.dto.UserDTO;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginDao {
    @Autowired
    private JdbcTemplate template;

    public UserDTO getUserId(String username, String password) {
        String sql = "select USER_ID, USERNAME, EMAIL, TOKEN, ROLE from tbl_user u " +
                "JOIN tbl_password p ON u.USER_ID = p.USER_ID_FK " +
                "JOIN tbl_token t ON t.USER_ID_FK = u.USER_ID " +
                "WHERE u.USERNAME = ? AND p.PASSWORD = ?;";
        try {
            return template.queryForObject(sql, new Object[]{username, password}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int registerUser(RegisterDTO registerDTO) {
        String sql = "INSERT INTO TBL_USER(USERNAME, EMAIL, ROLE, FIRSTNAME, LASTNAME) VALUES(?,?,'USER',?,?);";
        return template.update(sql, new Object[]{registerDTO.getUsername(), registerDTO.getEmail(),
                registerDTO.getFirstName(), registerDTO.getLastName()});
    }

    public long getLastInsertedUser(){
        String sql = "SELECT last_insert_id();";
        return template.queryForLong(sql);
    }

    public int registerPasswordForUser(RegisterDTO registerDTO, long lastInsertedUser){
        String sql = "INSERT INTO TBL_PASSWORD(PASSWORD, USER_ID_FK) VALUES(?, ?);";
        return template.update(sql, new Object[]{registerDTO.getPassword(), lastInsertedUser});
    }

    public int registerTokenForUser(String token, long lastInsteredUser){
        String sql = "INSERT INTO TBL_TOKEN(TOKEN, USER_ID_FK) VALUES(?,?);";
        return template.update(sql,new Object[]{token,lastInsteredUser});
    }

    public UserDTO getTokenCheck(String token) {
        String sql = "select USER_ID, USERNAME, EMAIL, TOKEN, ROLE from tbl_user u " +
                "JOIN tbl_password p ON u.USER_ID = p.USER_ID_FK " +
                "JOIN tbl_token t ON t.USER_ID_FK = u.USER_ID " +
                "WHERE t.TOKEN = ?;";
        try {
            return template.queryForObject(sql, new Object[]{token}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserDTO getTokenCheckForDoctor(String token) {
        String sql = "select USER_ID, USERNAME, EMAIL, TOKEN, ROLE from tbl_user u " +
                "JOIN tbl_password p ON u.USER_ID = p.USER_ID_FK " +
                "JOIN tbl_token t ON t.USER_ID_FK = u.USER_ID " +
                "WHERE t.TOKEN = ? and u.ROLE = 'DOCTOR';";
        try {
            return template.queryForObject(sql, new Object[]{token}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
