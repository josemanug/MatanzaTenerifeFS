package com.MatanzaTenerifeFS.backend.Entidades.User.DTOs;

public record LoginRequest(
        String username,
        String password
) {
}
