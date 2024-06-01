package br.com.desafio_ibm.domain.usecase.transacao.validacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.excecao.RegraNegocioException;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarIdConta implements ValidadorTransacao{
    private final ContaRepository contaRepository;

    public ValidarIdConta(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void validar(CadastroTransacaoDto cadastroTransacaoDto) {
        if(cadastroTransacaoDto.idConta() == null
                || !this.contaRepository.existsById(cadastroTransacaoDto.idConta())) {
            throw new RegraNegocioException("Não foi encontrado conta com este id." +
                    " Envie o id de uma conta existente para realizar a transação");
        }
    }
}
