package com.mrjoi.messagejob.services;

import com.mrjoi.messagejob.model.Reserva;

import java.util.List;

public interface ReservaService {

    public List<Reserva> listReservasPending();
    public void setHasEmailTrue(Long reserva_id);
}
