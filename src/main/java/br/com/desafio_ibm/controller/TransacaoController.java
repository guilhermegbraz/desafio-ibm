package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.service.transacao.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transacao")
@CrossOrigin()
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewTransacaoDto> detalharTransacao(@PathVariable long id) {
        ViewTransacaoDto transacaoDto = this.transacaoService.detalharTransacao(id);

        return ResponseEntity.ok(transacaoDto);
    }

    @PostMapping
    public ResponseEntity<ViewTransacaoDto> cadastrarTransacao
            (@RequestBody @Valid CadastroTransacaoDto cadastroTransacaoDto, UriComponentsBuilder uriBuilder) {
        ViewTransacaoDto transacaoDto = this.transacaoService.cadastrarTransacao(cadastroTransacaoDto);
        URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(transacaoDto.id()).toUri();

        return ResponseEntity.created(uri).body(transacaoDto);
    }
}
