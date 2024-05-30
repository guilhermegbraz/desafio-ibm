package br.com.desafio_ibm.service.transacao.validacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;

public interface ValidadorTransacao {
    void validar(CadastroTransacaoDto cadastroTransacaoDto);
}
