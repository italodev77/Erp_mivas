package dev.enterprisemanager.mivas.config.security;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}