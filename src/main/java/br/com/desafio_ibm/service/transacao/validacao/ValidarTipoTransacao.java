package br.com.desafio_ibm.service.transacao.validacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.excecao.RegraNegocioClienteException;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class ValidarTipoTransacao implements ValidadorTransacao{
    private final List<String> TIPOS_VALIDOS = Arrays.asList("CREDITO", "DEBITO");
    @Override
    public void validar(CadastroTransacaoDto cadastroTransacaoDto) {
        if(cadastroTransacaoDto.tipo() == null ||
                cadastroTransacaoDto.tipo().trim().isEmpty() ||
                !this.TIPOS_VALIDOS.contains(cadastroTransacaoDto.tipo().toUpperCase())
        )
                throw new RegraNegocioClienteException("Tipo de transação inválido, os tipos válidos " +
                        "são \"CREDITO\" ou \"DEBITO\". Envie na requisição sem acentos -- " + cadastroTransacaoDto.tipo());

    }
}
