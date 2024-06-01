package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.domain.usecase.transacao.BuscarTransacaoUsecase;
import br.com.desafio_ibm.domain.usecase.transacao.CadastrarTransacaoUsecase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transacao")
@CrossOrigin()
public class TransacaoController {

    private final CadastrarTransacaoUsecase cadastrarTransacao;
    private final BuscarTransacaoUsecase buscarTransacaoUsecase;

    public TransacaoController(CadastrarTransacaoUsecase cadastrarTransacao, BuscarTransacaoUsecase buscarTransacaoUsecase) {
        this.cadastrarTransacao = cadastrarTransacao;
        this.buscarTransacaoUsecase = buscarTransacaoUsecase;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ViewTransacaoDto> detalharTransacao(@PathVariable long id) {
        ViewTransacaoDto transacaoDto = this.buscarTransacaoUsecase.detalharTransacao(id);

        return ResponseEntity.ok(transacaoDto);
    }

    @PostMapping
    public ResponseEntity<ViewTransacaoDto> cadastrarTransacao
            (@RequestBody @Valid CadastroTransacaoDto cadastroTransacaoDto, UriComponentsBuilder uriBuilder) {
        ViewTransacaoDto transacaoDto = this.cadastrarTransacao.cadastrarTransacao(cadastroTransacaoDto);
        URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(transacaoDto.id()).toUri();

        return ResponseEntity.created(uri).body(transacaoDto);
    }
}
