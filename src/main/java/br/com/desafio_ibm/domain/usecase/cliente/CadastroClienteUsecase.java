package br.com.desafio_ibm.domain.usecase.cliente;

import br.com.desafio_ibm.domain.repository.ClienteRepository;
import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.dto.ViewClienteDto;
import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.domain.entities.ClienteEntity;
import br.com.desafio_ibm.domain.usecase.cliente.validacao.ValidacoesCliente;
import br.com.desafio_ibm.domain.usecase.conta.CriarContaUsecase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroClienteUsecase {
    private final List<ValidacoesCliente> validacoesClientes;
    private final ClienteRepository clienteRepository;
    private final CriarContaUsecase criarContaUsecase;

    public CadastroClienteUsecase(List<ValidacoesCliente> validacoesClientes, ClienteRepository clienteRepository, CriarContaUsecase criarContaUsecase) {
        this.validacoesClientes = validacoesClientes;
        this.clienteRepository = clienteRepository;
        this.criarContaUsecase = criarContaUsecase;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ViewClienteDto cadastrarCliente(CadastroClienteDto cadastroClienteDto) {
        this.validacoesClientes.forEach(validacao -> validacao.validar(cadastroClienteDto));
        ClienteEntity clienteEntity = cadastroClienteDto.toClienteEntity();
        this.clienteRepository.save(clienteEntity);
        ViewContaDto contaDto = (cadastroClienteDto.numeroConta() == null || cadastroClienteDto.numeroConta().isBlank()) ?
                this.criarContaUsecase.executar(clienteEntity)
                : this.criarContaUsecase.executar(clienteEntity, cadastroClienteDto.numeroConta());

        return new ViewClienteDto(clienteEntity, contaDto);
    }
}
