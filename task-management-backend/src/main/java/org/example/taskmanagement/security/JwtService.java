package org.example.taskmanagement.security;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
private String secretKey;
    
    private SecretKey getSignInKey() {

    return Keys.hmacShaKeyFor(
            secretKey.getBytes(StandardCharsets.UTF_8)
    );

}

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(
            String token
    ) {

        return extractAllClaims(token)
                .getSubject();
    }

    private Claims extractAllClaims(
            String token
    ) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(
            String token,
            String email
    ) {

        String username =
                extractUsername(token);

        return username.equals(email)
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(
            String token
    ) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}
