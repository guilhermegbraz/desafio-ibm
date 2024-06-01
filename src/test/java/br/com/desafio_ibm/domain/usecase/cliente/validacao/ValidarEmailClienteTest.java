package br.com.desafio_ibm.domain.usecase.cliente.validacao;

import br.com.desafio_ibm.domain.repository.ClienteRepository;
import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.excecao.RegraNegocioException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ValidarEmailClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ValidarEmailCliente validarEmailCliente;

    @BeforeEach
    void setUp() {
        validarEmailCliente = new ValidarEmailCliente(clienteRepository);
    }

    @Test
    void deveLancarExcecaoQuandoEmailDoClienteForNulo() {
        CadastroClienteDto clienteDto = new CadastroClienteDto("João", 30, null, null);
        Assertions.assertThrows(RegraNegocioException.class, () -> {
            validarEmailCliente.validar(clienteDto);
        });
    }

    @Test
    void deveLancarExcecaoQuandoEmailDoClienteForInvalido() {
        CadastroClienteDto clienteDto = new CadastroClienteDto("João", 30, "email.invalido", null);
        Assertions.assertThrows(RegraNegocioException.class, () -> {
            validarEmailCliente.validar(clienteDto);
        });
    }

    @Test
    void deveLancarExcecaoQuandoEmailDoClienteJaExiste() {
        String emailExistente = "joao@example.com";
        CadastroClienteDto clienteDto = new CadastroClienteDto("João", 30, emailExistente, null);
        BDDMockito.given(clienteRepository.existsByEnderecoEmail(emailExistente)).willReturn(true);

        Assertions.assertThrows(RegraNegocioException.class, () -> {
            validarEmailCliente.validar(clienteDto);
        });
    }

    @Test
    void naoDeveLancarExcecaoQuandoEmailDoClienteForValido() {
        String emailNovo = "novo_email@example.com";
        CadastroClienteDto clienteDto = new CadastroClienteDto("João", 30, emailNovo, null);
        BDDMockito.given(clienteRepository.existsByEnderecoEmail(emailNovo)).willReturn(false);

        Assertions.assertDoesNotThrow(() -> {
            validarEmailCliente.validar(clienteDto);
        });
    }
}