package dev.enterprisemanager.mivas.config.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTUserData {
    private Long userId;
    private String email;
    private Long enterpriseId;
}