package ru.aston.securityjwt.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.securityjwt.dto.JWTRequest;
import ru.aston.securityjwt.service.TestTokenService;

@RestController
@RequiredArgsConstructor
public class TestTokenController {
    private final TestTokenService testTokenService;
    @GetMapping("/tokentest")
    public HttpStatus testAuthToken(HttpServletRequest httpServletRequest){
                if(testTokenService.testToken(httpServletRequest)){
                    return HttpStatus.OK;
                }else{
                    return HttpStatus.FORBIDDEN;
                }
    }
}
