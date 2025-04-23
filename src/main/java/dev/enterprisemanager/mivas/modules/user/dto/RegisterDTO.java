package dev.enterprisemanager.mivas.modules.user.dto;

import dev.enterprisemanager.mivas.modules.user.enums.UserRole;

public record RegisterDTO(String name,String email, String password, UserRole role) {
}
