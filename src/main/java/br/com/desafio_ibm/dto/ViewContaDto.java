package br.com.desafio_ibm.dto;

import br.com.desafio_ibm.domain.entities.ContaEntity;

import java.math.BigDecimal;

public record ViewContaDto(
        Long id,
        String numeroConta,
        BigDecimal saldo,
        BigDecimal creditoDisponivel,
        ViewClienteDto cliente
) {


    public ViewContaDto(ContaEntity contaBancaria) {
        this(contaBancaria.getId(), contaBancaria.getNumeroConta()
                , contaBancaria.getSaldo(), contaBancaria.getCreditoDisponivel(), null);
    }

    public ViewContaDto(ContaEntity contaBancaria, ViewClienteDto clienteDto) {
        this(contaBancaria.getId(), contaBancaria.getNumeroConta()
                , contaBancaria.getSaldo(), contaBancaria.getCreditoDisponivel(), clienteDto);
    }
}
