package br.com.desafio_ibm.model.repository;

import br.com.desafio_ibm.model.entities.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsByEnderecoEmail(String email);

    Page<ClienteEntity> findAll(Pageable paginacao);

}
