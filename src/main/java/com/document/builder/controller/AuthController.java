package com.document.builder.controller;

import com.document.builder.dto.LoginRequestDto;
import com.document.builder.dto.LoginResponseDto;
import com.document.builder.dto.SignupRequestDto;
import com.document.builder.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth/api")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequestDto));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(signUpRequest));
    }
}
