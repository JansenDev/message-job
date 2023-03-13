package com.mrjoi.messagejob.repository;

import com.mrjoi.messagejob.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente findClienteByIdLogin(Long idLogin);
}
