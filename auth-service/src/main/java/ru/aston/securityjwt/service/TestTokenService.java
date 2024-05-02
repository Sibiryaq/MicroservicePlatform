package ru.aston.securityjwt.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.aston.securityjwt.utils.JwtTokenUtils;

@RequiredArgsConstructor
@Slf4j
@Service
public class TestTokenService {
    private final JwtTokenUtils jwtTokenUtils;

    public boolean testToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if(authHeader == null) {
            return false;
        }
        if (authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                log.debug("Токен просрочен");
                return false;
            } catch (SignatureException e) {
                log.debug("Подпись токена не верна");
                return false;
            }
        }
        log.error("Токен {}, Имя {}", jwt, username);
        return true;
    }
}




