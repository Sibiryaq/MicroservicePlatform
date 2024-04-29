package ru.aston.securityjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.aston.securityjwt.dto.JWTRequest;
import ru.aston.securityjwt.dto.JWTResponse;
import ru.aston.securityjwt.dto.RegistrationUserDto;
import ru.aston.securityjwt.dto.UserDto;
import ru.aston.securityjwt.entities.User;
import ru.aston.securityjwt.exceptions.AppError;
import ru.aston.securityjwt.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private  final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JWTRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "passwords are different"), HttpStatus.BAD_REQUEST);
        }
        if(userService.findByUsername(registrationUserDto.getUsername()).isPresent()){
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "User already exists"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}
