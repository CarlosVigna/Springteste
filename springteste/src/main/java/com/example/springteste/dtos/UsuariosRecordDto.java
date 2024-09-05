package com.example.springteste.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuariosRecordDto(@NotBlank String nome, @NotNull String email,@NotNull String senha) {
}
