package com.example.springteste.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContasRecordDto (@NotBlank String nome, @NotNull BigDecimal saldo){

}
