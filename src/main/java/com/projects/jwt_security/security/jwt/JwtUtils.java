package com.projects.jwt_security.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt_secret}")
    private String secret_key;

    public String generateToken(String username) {
        Date expiredDate = Date.from(ZonedDateTime.now().plusMinutes(10).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuer("ADMIN")
                .withIssuedAt(new Date())
                .withExpiresAt(expiredDate)
                .sign(Algorithm.HMAC256(secret_key));
    }

    public String verificationTokenAndRetrieve(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret_key))
                .withIssuer("ADMIN")
                .withSubject("User details")
                .build();

        DecodedJWT verifiedToken = verifier.verify(token);

        return verifiedToken.getClaim("username").asString();
    }
}
