package br.com.desafio_ibm.domain.usecase.cliente;

import br.com.desafio_ibm.domain.repository.ClienteRepository;
import br.com.desafio_ibm.dto.ViewClienteDto;
import br.com.desafio_ibm.domain.entities.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarClienteUsecase {
    private final ClienteRepository clienteRepository;

    public BuscarClienteUsecase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ViewClienteDto detalharCliente(long id) {
        ClienteEntity cliente = this.clienteRepository.getReferenceById(id);
        return new ViewClienteDto(cliente);
    }

    public Page<ViewClienteDto> listarClientesPaginado(Pageable paginacao) {
        return this.clienteRepository.findAll(paginacao).map(ViewClienteDto::new);
    }
}
