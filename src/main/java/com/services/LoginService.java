package com.services;

import com.dao.LoginDao;
import com.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    @Autowired
    LoginDao loginDao;

    public ResponseEntity<?> loginActionCheck(String username, String password){
        HashMap<String, String> map = new HashMap<String, String>();
        UserDTO userDTO =  loginDao.getUserId(username, password);
        if(userDTO != null){
            map.put("userId", String.valueOf(userDTO.getUserId()));
            map.put("token", userDTO.getToken());
            map.put("redirectSide","/userHome");
            return new ResponseEntity(map, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> tokenCheck(String token){
        UserDTO userDTO = loginDao.getTokenCheck(token);
        if(userDTO!=null){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public boolean checkAuth(String token){
        UserDTO userDTO = loginDao.getTokenCheck(token);
        if(userDTO!=null){
            return true;
        }
        return false;
    }
}
