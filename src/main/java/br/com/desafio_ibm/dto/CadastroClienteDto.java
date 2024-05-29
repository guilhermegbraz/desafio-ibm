package br.com.desafio_ibm.dto;

import br.com.desafio_ibm.model.entities.ClienteEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroClienteDto(
        @NotBlank(message = "O atributo \"Nome\" é obrigatório")
        @Size(min= 1, max=100, message = "Nome deve possuir entre 1 e 100 caracteres")
        String nome,
        int idade,
        @Email(message = "O e-mail deve ser válido")
        @NotBlank(message = "O atributo \"E-mail\" é obrigatório")
        @Size(max = 100, message = "O atributo \"E-mail\" deve possuir no máximo 100 caracteres")
        String email,

        @Nullable
        @Size(max = 10, message = "O atributo \"Numero conta\" deve possuir no máximo 10 digitos")
        String numeroConta
)
{
    public ClienteEntity toClienteEntity() {
        return new ClienteEntity(this.nome, this.idade, this.email);
    }

}
