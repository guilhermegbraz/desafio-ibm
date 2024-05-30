package br.com.desafio_ibm.model.repository;

import br.com.desafio_ibm.model.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    boolean existsByNumeroConta(String numeroConta);
}
