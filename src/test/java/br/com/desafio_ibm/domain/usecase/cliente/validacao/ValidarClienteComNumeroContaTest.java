package br.com.desafio_ibm.domain.usecase.cliente.validacao;

import br.com.desafio_ibm.domain.repository.ContaRepository;
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
class ValidarClienteComNumeroContaTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ValidarClienteComNumeroConta validarClienteComNumeroConta;

    @BeforeEach
    void setUp() {
        validarClienteComNumeroConta = new ValidarClienteComNumeroConta(contaRepository);
    }
    @Test
    void deveriaPermitirCadastroDeClienteComNumeroContaNull() {
        CadastroClienteDto clienteDto =
                new CadastroClienteDto("Guilherme", 26, "g@email.com", null);

        Assertions.assertDoesNotThrow(() -> {
            validarClienteComNumeroConta.validar(clienteDto);
        });
    }
    @Test
    void deveriaPermitirCadastroDeClienteComNumeroContaVazio() {
        CadastroClienteDto clienteDto =
                new CadastroClienteDto("Guilherme", 26, "g@email.com", " ");

        Assertions.assertDoesNotThrow(() -> {
            validarClienteComNumeroConta.validar(clienteDto);
        });
    }

    @Test
    void deveLancarExcecaoQuandoNumeroContaInexistente() {
        CadastroClienteDto clienteDto =
                new CadastroClienteDto("Guilherme", 26, "g@email.com", "0225");
        BDDMockito.given(contaRepository.existsByNumeroConta(clienteDto.numeroConta())).willReturn(Boolean.FALSE);

        Assertions.assertDoesNotThrow(() -> {
            validarClienteComNumeroConta.validar(clienteDto);
        });
    }

    @Test
    void deveLancarExcecaoQuandoNumeroContaJaExiste() {
        CadastroClienteDto clienteDto =
                new CadastroClienteDto("Guilherme", 26, "g@email.com", "0225");
        BDDMockito.given(contaRepository.existsByNumeroConta(clienteDto.numeroConta())).willReturn(Boolean.TRUE);

        Assertions.assertThrows(RegraNegocioException.class, () -> {
            validarClienteComNumeroConta.validar(clienteDto);
        });
    }

}