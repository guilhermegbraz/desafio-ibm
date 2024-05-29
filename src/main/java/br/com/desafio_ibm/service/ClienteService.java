package br.com.desafio_ibm.service;

import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.dto.ViewClienteDto;
import br.com.desafio_ibm.model.entities.ClienteEntity;
import br.com.desafio_ibm.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaService contaService;
    @Transactional(rollbackFor = Exception.class)
    public String cadastrarCliente(CadastroClienteDto cadastroClienteDto) {
        ClienteEntity clienteEntity = cadastroClienteDto.toClienteEntity();
        this.clienteRepository.save(clienteEntity);
        this.contaService.criarContaParaCliente(clienteEntity);
        return "new ViewClienteDto()";
    }

    public ClienteEntity detalharCliente(long id) {
        return this.clienteRepository.getReferenceById(id);
    }
}
