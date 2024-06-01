package br.com.desafio_ibm.domain.usecase.transacao.validacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;

public interface ValidadorTransacao {
    void validar(CadastroTransacaoDto cadastroTransacaoDto);
}
