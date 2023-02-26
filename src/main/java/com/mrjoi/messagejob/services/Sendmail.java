package com.mrjoi.messagejob.services;

import com.mrjoi.messagejob.model.Reserva;

public interface Sendmail<T> {

    Boolean Send(String to, Reserva data);
}
