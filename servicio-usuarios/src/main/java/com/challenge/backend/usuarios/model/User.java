package com.challenge.backend.usuarios.model;

public record User(
        Long id,
        String nombre,
        String email
) {
}
