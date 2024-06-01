package br.com.desafio_ibm.dto;

import br.com.desafio_ibm.domain.entities.ClienteEntity;

public record ViewClienteDto(
        long id,
        String nome,
        String email,
        int idade,
        ViewContaDto contaBancaria
) {

    public ViewClienteDto(ClienteEntity clienteEntity) {
        this(clienteEntity.getId(), clienteEntity.getNome()
                , clienteEntity.getEnderecoEmail(), clienteEntity.getIdade(),new ViewContaDto(clienteEntity.getConta()));
    }

    public ViewClienteDto(ClienteEntity clienteEntity, ViewContaDto contaDto) {
        this(clienteEntity.getId(), clienteEntity.getNome()
                , clienteEntity.getEnderecoEmail(), clienteEntity.getIdade(), contaDto);
    }
}
