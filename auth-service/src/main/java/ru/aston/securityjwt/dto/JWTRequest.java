package ru.aston.securityjwt.dto;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
