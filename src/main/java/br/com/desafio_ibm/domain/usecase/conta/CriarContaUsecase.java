package br.com.desafio_ibm.domain.usecase.conta;

import br.com.desafio_ibm.dto.ViewContaDto;
import br.com.desafio_ibm.domain.entities.ClienteEntity;
import br.com.desafio_ibm.domain.entities.ContaEntity;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CriarContaUsecase {
    private final ContaRepository contaRepository;

    public CriarContaUsecase(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ViewContaDto executar(ClienteEntity clienteEntity, String numeroConta) {
        ContaEntity contaBancaria = new ContaEntity();
        contaBancaria.setNumeroConta(numeroConta);
        contaBancaria.setCliente(clienteEntity);
        this.contaRepository.save(contaBancaria);

        return new ViewContaDto(contaBancaria);
    }

    public ViewContaDto executar(ClienteEntity clienteEntity) {
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
}
