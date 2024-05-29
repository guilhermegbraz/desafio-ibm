package br.com.desafio_ibm.dto;

import br.com.desafio_ibm.model.entities.ClienteEntity;

public record CadastroClienteDto(
        String nome,
        int idade,
        String email
)
{
    public ClienteEntity toClienteEntity() {
        return new ClienteEntity(this.nome, this.idade, this.email);
    }
}
