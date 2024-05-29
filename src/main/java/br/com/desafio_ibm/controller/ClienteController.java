package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.dto.ViewClienteDto;
import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.model.entities.ClienteEntity;
import br.com.desafio_ibm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<String> listarClientes() {
        System.out.println("GET FEITO");
        return ResponseEntity.ok("new ViewClienteDto()");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewClienteDto> detalharCliente(@PathVariable long id) {
        ClienteEntity c = this.clienteService.detalharCliente(id);
        ViewContaDto contaDto = new ViewContaDto(c.getConta().getId(), c.getConta().getNumeroConta(), c.getConta().getSaldo(), c.getConta().getCreditoDisponivel());
        ViewClienteDto clienteDto = new ViewClienteDto(c.getId(), c.getNome(), c.getEnderecoEmail(), contaDto);
        return ResponseEntity.ok(clienteDto);
    }
    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@RequestBody CadastroClienteDto cadastroClienteDto) {
        this.clienteService.cadastrarCliente(cadastroClienteDto);
        return ResponseEntity.ok("new ViewClienteDto()");
    }
}
