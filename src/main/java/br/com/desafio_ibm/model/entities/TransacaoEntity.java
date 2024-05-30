package br.com.desafio_ibm.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "transacao")
@Table(name = "transacao")
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    private BigDecimal valor;
    private LocalDateTime data;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "id_conta", nullable = false)
    private ContaEntity conta;

    public TransacaoEntity(TipoTransacao tipo, BigDecimal valor, ContaEntity conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.conta = conta;
    }
}

