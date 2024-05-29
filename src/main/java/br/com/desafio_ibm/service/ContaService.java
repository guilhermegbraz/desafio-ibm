package br.com.desafio_ibm.service;

import br.com.desafio_ibm.model.entities.ClienteEntity;
import br.com.desafio_ibm.model.entities.ContaEntity;
import br.com.desafio_ibm.model.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    public void criarContaParaCliente(ClienteEntity clienteEntity) {
        ContaEntity contaBancaria = new ContaEntity();
        contaBancaria.setNumeroConta(gerarNumeroAConta());
        contaBancaria.setCliente(clienteEntity);
        this.contaRepository.save(contaBancaria);
        System.out.println(contaBancaria);
    }

    private static String gerarNumeroAConta() {
        Random random = new Random();
        StringBuilder numeroConta = new StringBuilder();
        for (int i = 0; i < 10; i++)
            numeroConta.append(random.nextInt(10));

        return numeroConta.toString();
    }
}
