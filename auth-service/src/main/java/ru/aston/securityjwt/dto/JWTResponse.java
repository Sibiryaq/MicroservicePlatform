package ru.aston.securityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JWTResponse {
    private String token;
}
