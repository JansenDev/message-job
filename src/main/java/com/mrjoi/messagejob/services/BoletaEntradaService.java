package com.mrjoi.messagejob.services;

import com.mrjoi.messagejob.model.BoletaEntrada;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoletaEntradaService {

    List<BoletaEntrada> listTicketsPending();
    void setHasEmailTrue(Integer numBoleta);
}
