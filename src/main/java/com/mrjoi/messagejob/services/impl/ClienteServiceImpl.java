package com.mrjoi.messagejob.services.impl;

import com.mrjoi.messagejob.model.Cliente;
import com.mrjoi.messagejob.repository.ClienteRepository;
import com.mrjoi.messagejob.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    public Cliente findByid(Long id) {
        return (Cliente) clienteRepository.findAll();
    }

    @Override
    public Cliente findClienteByIdLogin(Long id) {
        return clienteRepository.findClienteByIdLogin(id);
    }
}
