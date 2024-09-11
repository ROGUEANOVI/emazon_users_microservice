package com.pragma.emazon.users_microservice.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.pragma.emazon.users_microservice.infrastructure.constant.SecurityMessages;
import com.pragma.emazon.users_microservice.infrastructure.out.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.issuer}")
    private String issuer;

    public String createToken(Authentication authentication ) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(SecurityMessages.DELIMITER));

        return JWT
                .create()
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer(issuer)
                .withSubject(userDetails.getUsername())
                .withClaim(SecurityMessages.USER_ID, userDetails.getId())
                .withClaim(SecurityMessages.AUTHORITIES, authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityMessages.JWT_EXPIRATION_IN_MILLISECONDS))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();

            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(SecurityMessages.INVALID_TOKEN);
        }
    }

    public String extractUsername(DecodedJWT decodedToken) {
        return decodedToken.getSubject();
    }

    public Claim extactSpecifiedClaim(DecodedJWT decodedToken, String claimName) {
        return decodedToken.getClaim(claimName);
    }

    public Map<String, Claim> extractClaims(DecodedJWT decodedToken) {
        return decodedToken.getClaims();
    }
}
