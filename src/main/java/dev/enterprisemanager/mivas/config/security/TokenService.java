package dev.enterprisemanager.mivas.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.enterprisemanager.mivas.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${mivas.security.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("enterpriseId", user.getEnterprise().getId())
                .withClaim("pathDb", user.getEnterprise().getPathdb())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);
            return Optional.of(JWTUserData.builder()
                    .email(decode.getSubject())
                    .userId(decode.getClaim("userId").asLong())
                    .enterpriseId(decode.getClaim("enterpriseId").asLong())
                    .pathDb(decode.getClaim("pathDb").asString())
                    .build());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
