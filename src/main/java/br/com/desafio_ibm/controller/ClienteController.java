package br.com.desafio_ibm.controller;

import br.com.desafio_ibm.dto.ViewClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping
    public ResponseEntity<String> listarClientes() {

        return ResponseEntity.ok("new ViewClienteDto()");
    }
}
