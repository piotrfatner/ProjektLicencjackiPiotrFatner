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
            if(userDTO.getRole().equals("DOCTOR")){
                map.put("redirectSide","/doctorHome");
                map.put("isDoctor","true");
            }else{
                map.put("redirectSide","/userHome");
                map.put("isDoctor","false");
            }
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

    public ResponseEntity<?> tokenCheckForDoctor(String token){
        UserDTO userDTO = loginDao.getTokenCheckForDoctor(token);
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

    public boolean checkAuthForDoctor(String token){
        UserDTO userDTO = loginDao.getTokenCheckForDoctor(token);
        if(userDTO!=null){
            return true;
        }
        return false;
    }
}
