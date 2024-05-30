package br.com.desafio_ibm.dto;

import br.com.desafio_ibm.model.entities.TipoTransacao;
import br.com.desafio_ibm.model.entities.TransacaoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViewTransacaoDto(
        Long id,
        Long idConta,
        TipoTransacao tipo,
        BigDecimal valor,
        LocalDateTime data,
        String descricao
) {
    public ViewTransacaoDto(TransacaoEntity transacaoEntity) {
        this(transacaoEntity.getId(), transacaoEntity.getConta().getId(), transacaoEntity.getTipo()
                , transacaoEntity.getValor(), transacaoEntity.getData(), transacaoEntity.getDescricao());
    }
}
