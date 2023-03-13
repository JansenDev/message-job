package com.mrjoi.messagejob.job.impl;

import com.mrjoi.messagejob.job.MessageJob;
import com.mrjoi.messagejob.model.BoletaEntrada;
import com.mrjoi.messagejob.model.Cliente;
import com.mrjoi.messagejob.model.Reserva;
import com.mrjoi.messagejob.services.BoletaEntradaService;
import com.mrjoi.messagejob.services.ClienteService;
import com.mrjoi.messagejob.services.ReservaService;
import com.mrjoi.messagejob.services.Sendmail;
import com.mrjoi.messagejob.utils.VoucherTemplate;
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
    @Autowired
    private BoletaEntradaService boletaEntradaService;
    @Autowired
    private ClienteService clienteService;

    @Scheduled(cron = "${cron.expression}", zone = "GMT-5")
    public void Run() {
        List<Reserva> users = reservaService.listReservasPending();
        VoucherTemplate vt = new VoucherTemplate();
        for (Reserva reserva : users) {
            String voucherReservaTemplate = vt.getVoucherReservaTemplate(reserva);
            String emailClient = reserva.getEmail();
            boolean isSent = sendMail.Send(voucherReservaTemplate, emailClient);
            if (isSent) {
                reservaService.setHasEmailTrue(reserva.getIdReserva());
            }
        }
    }

    @Scheduled(cron = "${cron.expression}", zone = "GMT-5")
    public void RunJobTickets() {
        List<BoletaEntrada> tickets = boletaEntradaService.listTicketsPending();
        VoucherTemplate utils = new VoucherTemplate();

        for (BoletaEntrada ticket : tickets) {
            String voucherEntradaTemplate = utils.getVoucherEntradaTemplate(ticket);
            Long idLogin = (long) ticket.getIdLogin();
            Cliente clientFound = clienteService.findClienteByIdLogin(idLogin);
            String clientEmail = clientFound.getCorreo();
            boolean isSent = sendMail.Send(voucherEntradaTemplate, clientEmail);
            if (isSent) {
                Integer numBoleta = ticket.getNumBoleta();
                boletaEntradaService.setHasEmailTrue(numBoleta);
            }
        }
    }


}
