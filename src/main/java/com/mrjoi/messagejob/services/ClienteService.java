package com.mrjoi.messagejob.services;

import com.mrjoi.messagejob.model.Cliente;

public interface ClienteService {
    Cliente findByid(Long id);
    Cliente findClienteByIdLogin(Long id);
}
