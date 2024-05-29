package br.com.desafio_ibm.service;

import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.excecao.RegraNegocioClienteException;
import br.com.desafio_ibm.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarEmailCliente implements ValidacoesCliente{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void validar(CadastroClienteDto cliente) {
        if(this.clienteRepository.existsByEnderecoEmail(cliente.email()))
            throw new RegraNegocioClienteException("E-mail já cadastrado!");
    }
}
