package com.mrjoi.messagejob.repository;

import com.mrjoi.messagejob.model.Reserva;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {


    @Query("SELECT u FROM Reserva u WHERE u.hasEmail = false")
    public List<Reserva> listReservasPending();
    @Modifying
    @Query("UPDATE Reserva r SET r.hasEmail = true WHERE r.idReserva = :reserva_id")
    public void setHasEmailTrue(@Param("reserva_id") Long reserva_id);

}
