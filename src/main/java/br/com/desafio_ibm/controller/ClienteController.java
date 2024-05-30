package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.dto.ViewClienteDto;

import br.com.desafio_ibm.service.cliente.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import  java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<ViewClienteDto>> listarClientes(@PageableDefault(size=15, sort= "nome") Pageable paginacao) {

        return ResponseEntity.ok(this.clienteService.listarClientesPaginado(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewClienteDto> detalharCliente(@PathVariable long id) {
        ViewClienteDto clienteDto = this.clienteService.detalharCliente(id);
        return ResponseEntity.ok(clienteDto);
    }
    @PostMapping
    public ResponseEntity<ViewClienteDto> cadastrarCliente(@RequestBody @Valid CadastroClienteDto cadastroClienteDto,
                                                           UriComponentsBuilder uriBuilder) {
        ViewClienteDto clienteCadastrado = this.clienteService.cadastrarCliente(cadastroClienteDto);
        URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteCadastrado.id()).toUri();

        return ResponseEntity.created(uri).body(clienteCadastrado);
    }
}
