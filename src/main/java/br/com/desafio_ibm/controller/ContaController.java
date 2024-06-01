package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.domain.usecase.cliente.BuscarClienteUsecase;
import br.com.desafio_ibm.domain.usecase.conta.BuscarContaUsecase;
import br.com.desafio_ibm.domain.usecase.conta.CriarContaUsecase;
import br.com.desafio_ibm.domain.usecase.transacao.BuscarTransacaoUsecase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/conta")
@CrossOrigin()
public class ContaController {

    private final BuscarContaUsecase buscarContaUsecase;
    private final BuscarTransacaoUsecase buscarTransacaoUsecase;
    public ContaController(CriarContaUsecase criarContaUsecase, BuscarClienteUsecase buscarClienteUsecase, BuscarContaUsecase buscarContaUsecase, BuscarTransacaoUsecase buscarTransacaoUsecase) {
        this.buscarContaUsecase = buscarContaUsecase;
        this.buscarTransacaoUsecase = buscarTransacaoUsecase;
    }


    @GetMapping
    public ResponseEntity<Page<ViewContaDto>> listarContas(@PageableDefault(size=15, sort= "numeroConta") Pageable paginacao) {
        return ResponseEntity.ok(this.buscarContaUsecase.listarPaginado(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewContaDto> detalharConta(@PathVariable long id) {
        ViewContaDto contaDto = this.buscarContaUsecase.detalhar(id);
        return ResponseEntity.ok(contaDto);
    }

    @GetMapping("/{id}/transacao")
    public ResponseEntity<Page<ViewTransacaoDto>> listarTransacoesContas
            (@PathVariable long id, @PageableDefault(size=15, sort= "data", direction = Sort.Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok(this.buscarTransacaoUsecase.listarPorContasPaginado(id, paginacao));
    }
}
