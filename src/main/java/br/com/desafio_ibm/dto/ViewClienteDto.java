package br.com.desafio_ibm.dto;

public record ViewClienteDto(
        long id,
        String nome,
        String email,
        ViewContaDto contaBancaria
) {
}
