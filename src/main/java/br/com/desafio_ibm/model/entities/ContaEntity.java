package br.com.desafio_ibm.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "conta")
@Table(name = "conta_bancaria")
public class ContaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroConta;
    private BigDecimal saldo;
    private BigDecimal creditoDisponivel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    public ContaEntity() {
        this.saldo = BigDecimal.ZERO;
        this.creditoDisponivel = BigDecimal.ZERO;
    }

}
