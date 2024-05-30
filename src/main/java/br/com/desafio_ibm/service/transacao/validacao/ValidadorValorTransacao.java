package br.com.desafio_ibm.service.transacao.validacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.excecao.RegraNegocioClienteException;
import br.com.desafio_ibm.model.entities.ContaEntity;
import br.com.desafio_ibm.model.repository.ContaRepository;
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
                throw new RegraNegocioClienteException(cadastroTransacaoDto.tipo() + " insuficiente");
        }


    }
}
