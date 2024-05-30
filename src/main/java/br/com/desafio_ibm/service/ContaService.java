package br.com.desafio_ibm.service;

import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.excecao.RegraNegocioClienteException;
import br.com.desafio_ibm.model.entities.ClienteEntity;
import br.com.desafio_ibm.model.entities.ContaEntity;
import br.com.desafio_ibm.model.repository.ContaRepository;
import br.com.desafio_ibm.service.transacao.TransacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final TransacaoService transacaoService;

    public ContaService(ContaRepository contaRepository, TransacaoService transacaoService) {
        this.contaRepository = contaRepository;
        this.transacaoService = transacaoService;
    }

    public ViewContaDto criarContaParaCliente(ClienteEntity clienteEntity, String numeroConta) {
        ContaEntity contaBancaria = new ContaEntity();
        contaBancaria.setNumeroConta(numeroConta);
        contaBancaria.setCliente(clienteEntity);
        this.contaRepository.save(contaBancaria);

        return new ViewContaDto(contaBancaria);
    }

    public ViewContaDto criarContaParaCliente(ClienteEntity clienteEntity) {
        ContaEntity contaBancaria = new ContaEntity();
        String numeroConta = gerarNumeroAConta();
        while(this.contaRepository.existsByNumeroConta(numeroConta)) numeroConta = gerarNumeroAConta();
        contaBancaria.setNumeroConta(numeroConta);
        contaBancaria.setCliente(clienteEntity);
        this.contaRepository.save(contaBancaria);

        return new ViewContaDto(contaBancaria);
    }

    private static String gerarNumeroAConta() {
        Random random = new Random();
        StringBuilder numeroConta = new StringBuilder();
        for (int i = 0; i < 10; i++)
            numeroConta.append(random.nextInt(10));

        return numeroConta.toString();
    }

    public Page<ViewContaDto> listarContasPaginado(Pageable paginacao) {
        return this.contaRepository.findAll(paginacao).map(ViewContaDto::new);
    }

    public ViewContaDto detalharConta(long id) {
        return new ViewContaDto(this.contaRepository.getReferenceById(id));
    }

    public Page<ViewTransacaoDto> listarTransacaoContasPaginado(long id, Pageable paginacao) {
        if(!this.contaRepository.existsById(id)) throw new RegraNegocioClienteException("Não existe conta com esse id");
        return this.transacaoService.listarTransacaoContasPaginado(id, paginacao);
    }
}
