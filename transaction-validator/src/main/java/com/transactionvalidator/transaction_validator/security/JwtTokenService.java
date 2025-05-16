package com.transactionvalidator.transaction_validator.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtTokenService {
    private final Algorithm algorithm;

    public JwtTokenService(@Value("${jwt.secret.key}") String secretKey) {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    
    private static final String ISSUER = "transaction-api";

    public String generateToken(UserDetailsImplementation user) {
        try {
            return JWT.create()
            .withIssuer(ISSUER)
            .withIssuedAt(createDate(0))
            .withExpiresAt(createDate(60 * 60))
            .withSubject(user.getUsername())
            .sign(this.algorithm);
        }catch(JWTCreationException exception) {
            throw new JWTCreationException("Error in generate token", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            return JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build()
            .verify(token)
            .getSubject();
        } catch(JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido ou expirado");
        }
    }

    public Instant createDate(long offsetSeconds) {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusSeconds(offsetSeconds).toInstant();
    }
}
