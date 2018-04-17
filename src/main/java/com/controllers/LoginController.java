package com.controllers;

import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@$";

    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return loginService.loginActionCheck(loginDTO.getUsername(), loginDTO.getPassword());
    }


    @RequestMapping(value="/checkToken", method = RequestMethod.POST)
    public ResponseEntity<?> checkToken(@RequestBody UserDTO user){
        return loginService.tokenCheck(user.getToken());
    }

    public static String generateToken() {
        StringBuilder token = new StringBuilder(40);
        for (int i = 0; i < 40; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }
}
