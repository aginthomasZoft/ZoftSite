package com.zoft.solutions.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final Key SECRET_KEY = Keys.secretKeyFor(SIGNATURE_ALGORITHM);
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    public String generateToken(String username, String userRole) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", userRole)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY, SIGNATURE_ALGORITHM)
                .compact();
    }

    public Key getSecretKey() {
        return SECRET_KEY;
    }
}



