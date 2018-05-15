package com.controllers;

import com.dto.LoginDTO;
import com.dto.RegisterDTO;
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

    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return loginService.loginActionCheck(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @RequestMapping(value = "/registerAction", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return loginService.register(registerDTO);
    }


    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public ResponseEntity<?> checkToken(@RequestBody UserDTO user) {
        return loginService.tokenCheck(user.getToken());
    }

    @RequestMapping(value = "/checkTokenForDoctor", method = RequestMethod.POST)
    public ResponseEntity<?> checkTokenForDoctor(@RequestBody UserDTO user) {
        return loginService.tokenCheckForDoctor(user.getToken());
    }
}
