package ru.aston.securityjwt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.aston.securityjwt.dto.JWTRequest;
import ru.aston.securityjwt.dto.RegistrationUserDto;
import ru.aston.securityjwt.service.AuthService;


@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;



    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JWTRequest authRequest){
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        return authService.createNewUser(registrationUserDto);
    }
}
