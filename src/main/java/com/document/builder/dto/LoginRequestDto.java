package com.document.builder.dto;


import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private String role;
}
