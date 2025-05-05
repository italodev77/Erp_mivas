package dev.enterprisemanager.mivas.modules.user.dto;

public record LoginResponseDTO(
        String token,
        String name,
        String email,
        String role,
        Long enterpriseId,
        String enterpriseName
) {}

