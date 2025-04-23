package dev.enterprisemanager.mivas.modules.user.enums;

public enum ErrorCode {

    USUARIO_EMAIL_DUPLICADO("USR-001", "Email já cadastrado."),
    USUARIO_SENHA_FRACA("USR-002", "Senha não atende aos requisitos."),
    USUARIO_NAO_ENCONTRADO("USR-003", "Usuário não encontrado.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
    }