package com.mrjoi.messagejob.services.impl;

import com.mrjoi.messagejob.model.BoletaEntrada;
import com.mrjoi.messagejob.repository.BoletaEntradaRepository;
import com.mrjoi.messagejob.services.BoletaEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoletaEntradaServiceImpl implements BoletaEntradaService {

    @Autowired
    BoletaEntradaRepository boletaEntradaRepository;

    @Override
    public List<BoletaEntrada> listTicketsPending() {
        return boletaEntradaRepository.listticketsPending();
    }

    @Override
    public void setHasEmailTrue(Integer numBoleta) {
        boletaEntradaRepository.setHasEmailTrue(numBoleta);
    }
}
