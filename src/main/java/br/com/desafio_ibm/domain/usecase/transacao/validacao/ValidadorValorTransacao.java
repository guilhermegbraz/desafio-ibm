package br.com.desafio_ibm.domain.usecase.transacao.validacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.excecao.RegraNegocioException;
import br.com.desafio_ibm.domain.entities.ContaEntity;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorValorTransacao implements ValidadorTransacao{
    private final ContaRepository contaRepository;

    public ValidadorValorTransacao(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void validar(CadastroTransacaoDto cadastroTransacaoDto) {
        if(cadastroTransacaoDto.valor().compareTo(BigDecimal.ZERO) < 0) {
            ContaEntity contaEntity = this.contaRepository.getReferenceById(cadastroTransacaoDto.idConta());
            BigDecimal valorComparacao;
            if(cadastroTransacaoDto.tipo().equalsIgnoreCase("CREDITO")) valorComparacao = contaEntity.getCreditoDisponivel();
            else valorComparacao = contaEntity.getSaldo();

            if(cadastroTransacaoDto.valor().abs().compareTo(valorComparacao) > 0)
                throw new RegraNegocioException(cadastroTransacaoDto.tipo() + " insuficiente");
        }


    }
}
