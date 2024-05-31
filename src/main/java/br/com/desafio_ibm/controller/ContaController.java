package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.service.ContaService;
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

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<Page<ViewContaDto>> listarContas(@PageableDefault(size=15, sort= "numeroConta") Pageable paginacao) {
        return ResponseEntity.ok(this.contaService.listarContasPaginado(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewContaDto> detalharCliente(@PathVariable long id) {
        ViewContaDto contaDto = this.contaService.detalharConta(id);
        return ResponseEntity.ok(contaDto);
    }

    @GetMapping("/{id}/transacao")
    public ResponseEntity<Page<ViewTransacaoDto>> listarTransacoesContas
            (@PathVariable long id, @PageableDefault(size=15, sort= "data", direction = Sort.Direction.DESC) Pageable paginacao) {
        return ResponseEntity.ok(this.contaService.listarTransacaoContasPaginado(id, paginacao));
    }
}
