package com.mrjoi.messagejob.services;

import com.mrjoi.messagejob.model.Reserva;

public interface Sendmail<T> {

    Boolean Send(String voucherTemplate, String to, String contratoTempalte);
    Boolean SendEntradaVoucher(String voucherTemplate, String to );
}
