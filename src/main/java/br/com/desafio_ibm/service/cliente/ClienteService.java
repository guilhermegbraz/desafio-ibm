package br.com.desafio_ibm.service.cliente;

import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.dto.ViewClienteDto;
import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.model.entities.ClienteEntity;
import br.com.desafio_ibm.model.repository.ClienteRepository;
import br.com.desafio_ibm.service.ContaService;
import br.com.desafio_ibm.service.cliente.validacao.ValidacoesCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaService contaService;
    @Autowired
    private List<ValidacoesCliente> validacoesClientes;

    @Transactional(rollbackFor = RuntimeException.class)
    public ViewClienteDto cadastrarCliente(CadastroClienteDto cadastroClienteDto) {
        validacoesClientes.forEach(validacao -> validacao.validar(cadastroClienteDto));
        ClienteEntity clienteEntity = cadastroClienteDto.toClienteEntity();
        this.clienteRepository.save(clienteEntity);
        ViewContaDto contaDto = (cadastroClienteDto.numeroConta() == null || cadastroClienteDto.numeroConta().isBlank()) ?
                this.contaService.criarContaParaCliente(clienteEntity)
                : this.contaService.criarContaParaCliente(clienteEntity, cadastroClienteDto.numeroConta());

        return new ViewClienteDto(clienteEntity, contaDto);
    }

    public ViewClienteDto detalharCliente(long id) {
        ClienteEntity cliente = this.clienteRepository.getReferenceById(id);
        return new ViewClienteDto(cliente);
    }

    public Page<ViewClienteDto> listarClientesPaginado(Pageable paginacao) {
        return this.clienteRepository.findAll(paginacao).map(ViewClienteDto::new);
    }
}
