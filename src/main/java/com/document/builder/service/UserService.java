package com.document.builder.service;

import com.document.builder.dto.LoginRequestDto;
import com.document.builder.dto.LoginResponseDto;
import com.document.builder.dto.SignupRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto);

    String addUser(SignupRequestDto signUpRequest);
}
