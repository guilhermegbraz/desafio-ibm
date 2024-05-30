package br.com.desafio_ibm.model.repository;

import br.com.desafio_ibm.model.entities.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
}
