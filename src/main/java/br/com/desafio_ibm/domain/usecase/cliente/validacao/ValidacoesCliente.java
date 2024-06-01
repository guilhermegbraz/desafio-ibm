package br.com.desafio_ibm.domain.usecase.cliente.validacao;

import br.com.desafio_ibm.dto.CadastroClienteDto;

public interface ValidacoesCliente {
    void validar(CadastroClienteDto cliente);
}
