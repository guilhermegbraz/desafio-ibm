package br.com.desafio_ibm.dto;

import java.math.BigDecimal;

public record ViewContaDto(
        Long id,
        String numeroConta,
        BigDecimal saldo,
        BigDecimal creditoDisponivel
) {
}
