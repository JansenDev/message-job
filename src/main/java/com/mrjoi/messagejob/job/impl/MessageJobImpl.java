package com.mrjoi.messagejob.job.impl;

import com.mrjoi.messagejob.job.MessageJob;
import com.mrjoi.messagejob.model.Reserva;
import com.mrjoi.messagejob.services.ReservaService;
import com.mrjoi.messagejob.services.Sendmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MessageJobImpl implements MessageJob {
    @Autowired
    private Sendmail sendMail;
    @Autowired
    private ReservaService reservaService;
    private Reserva reserva;

    //    @Scheduled(cron = "${cron.expression}",zone = "GMT-5")
    @Scheduled(cron = "${cron.expression}", zone = "GMT-5")
    public void Run() {
        String email = "seguragjj25@gmail.com";
        List<Reserva> users = reservaService.listReservasPending();

        for (Reserva reserva : users) {
            boolean isSent = sendMail.Send(reserva.getEmail(), reserva);
            if (isSent) {
                reservaService.setHasEmailTrue(reserva.getIdReserva());
            }
        }

    }
}
