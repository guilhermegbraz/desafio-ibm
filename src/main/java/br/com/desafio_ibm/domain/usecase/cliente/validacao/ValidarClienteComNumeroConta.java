package br.com.desafio_ibm.domain.usecase.cliente.validacao;

import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.excecao.RegraNegocioException;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarClienteComNumeroConta implements ValidacoesCliente {
    private final ContaRepository contaRepository;

    public ValidarClienteComNumeroConta(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void validar(CadastroClienteDto cliente) {
        if (cliente.numeroConta() != null && !cliente.numeroConta().trim().isEmpty()
        && this.contaRepository.existsByNumeroConta(cliente.numeroConta()))
            throw new RegraNegocioException(
                    "Esse número de conta já existe, para criar um cliente, passe um número de" +
                    " conta único ou não envie o campo \"Numero conta\" para que a aplicação gere um"
            );
    }
}
