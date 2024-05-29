package br.com.desafio_ibm.dto;

import br.com.desafio_ibm.model.entities.ContaEntity;

import java.math.BigDecimal;

public record ViewContaDto(
        Long id,
        String numeroConta,
        BigDecimal saldo,
        BigDecimal creditoDisponivel
) {


    public ViewContaDto(ContaEntity contaBancaria) {
        this(contaBancaria.getId(), contaBancaria.getNumeroConta()
                , contaBancaria.getSaldo(), contaBancaria.getCreditoDisponivel());
    }
}
