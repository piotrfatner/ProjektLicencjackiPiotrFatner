package com.dao;

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

    public UserDTO getUserId(String username, String password){
        String sql = "select USER_ID, USERNAME, EMAIL, TOKEN, ROLE from tbl_user u " +
                "JOIN tbl_password p ON u.USER_ID = p.USER_ID_FK " +
                "JOIN tbl_token t ON t.USER_ID_FK = u.USER_ID " +
                "WHERE u.USERNAME = ? AND p.PASSWORD = ?;";
        try{
            return template.queryForObject(sql,new Object[] {username, password},new UserMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public UserDTO getTokenCheck(String token){
        String sql = "select USER_ID, USERNAME, EMAIL, TOKEN, ROLE from tbl_user u " +
                "JOIN tbl_password p ON u.USER_ID = p.USER_ID_FK " +
                "JOIN tbl_token t ON t.USER_ID_FK = u.USER_ID " +
                "WHERE t.TOKEN = ?;";
        try{
            return template.queryForObject(sql,new Object[] {token},new UserMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public UserDTO getTokenCheckForDoctor(String token){
        String sql = "select USER_ID, USERNAME, EMAIL, TOKEN, ROLE from tbl_user u " +
                "JOIN tbl_password p ON u.USER_ID = p.USER_ID_FK " +
                "JOIN tbl_token t ON t.USER_ID_FK = u.USER_ID " +
                "WHERE t.TOKEN = ? and u.ROLE = 'DOCTOR';";
        try{
            return template.queryForObject(sql,new Object[] {token},new UserMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
