package br.com.desafio_ibm.service.cliente.validacao;

import br.com.desafio_ibm.dto.CadastroClienteDto;

public interface ValidacoesCliente {
    void validar(CadastroClienteDto cliente);
}
