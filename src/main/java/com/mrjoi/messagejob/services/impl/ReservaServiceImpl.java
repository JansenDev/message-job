package com.mrjoi.messagejob.services.impl;

import com.mrjoi.messagejob.model.Reserva;
import com.mrjoi.messagejob.repository.ReservaRepository;
import com.mrjoi.messagejob.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;


    @Override
    public List<Reserva> listReservasPending() {
        return reservaRepository.listReservasPending();
    }

    @Override
    public void setHasEmailTrue(Long reserva_id) {
        reservaRepository.setHasEmailTrue(reserva_id);
    }

}
