package br.com.desafio_ibm.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CadastroTransacaoDto(
        @NotNull
        Long idConta,
        @NotBlank(message = "O atributo \"Tipo\" é obrigatório")
        String tipo,
        @NotNull(message = "O atributo \"Valor\" é obrigatório")
        BigDecimal valor,
        @Nullable
        @Size(max = 255, message = "A descrição deve possuir no máximo 255 caracteres")
        String descricao
) {
}
