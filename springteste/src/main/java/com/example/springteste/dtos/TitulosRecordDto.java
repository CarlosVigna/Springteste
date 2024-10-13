package com.example.springteste.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record TitulosRecordDto(
        @NotNull Date vencimento,
        @NotNull Long categoriaId,
        @NotNull BigDecimal valor,
        @NotNull String descricao,
        @NotNull String status)
{
}