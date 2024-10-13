package com.example.springteste.dtos;

import com.example.springteste.models.RegrasUsuarios;

public record RegistroDto(String nome, String email, String senha, RegrasUsuarios regra) {
}
