package br.com.desafio_ibm.domain.usecase.conta;

import br.com.desafio_ibm.dto.ViewClienteDto;
import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.domain.entities.ContaEntity;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarContaUsecase {
    private final ContaRepository contaRepository;

    public BuscarContaUsecase(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Page<ViewContaDto> listarPaginado(Pageable paginacao) {
        return this.contaRepository.findAll(paginacao).map(ViewContaDto::new);
    }

    public ViewContaDto detalhar(long id) {
        ContaEntity conta= this.contaRepository.getReferenceById(id);
        return new ViewContaDto(conta, new ViewClienteDto(conta.getCliente(), null));
    }
}
