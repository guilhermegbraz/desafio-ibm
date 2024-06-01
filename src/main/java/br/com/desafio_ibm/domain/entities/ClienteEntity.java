package br.com.desafio_ibm.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "cliente")
@Table(name= "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;
    private String enderecoEmail;

    @OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
    private ContaEntity conta;

    public ClienteEntity(String nome, int idade, String email) {
        this.nome = nome;
        this.enderecoEmail = email;
        this.idade = idade;
    }

}
