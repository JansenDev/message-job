package com.mrjoi.messagejob.repository;

import com.mrjoi.messagejob.model.BoletaEntrada;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletaEntradaRepository extends CrudRepository<BoletaEntrada, Integer> {

    @Query("SELECT u FROM BoletaEntrada u WHERE u.hasEmail = false or u.hasEmail = null")
    List<BoletaEntrada> listticketsPending();

    @Modifying
    @Query("UPDATE BoletaEntrada r SET r.hasEmail = true WHERE r.numBoleta = :numBoleta")
    public void setHasEmailTrue(@Param("numBoleta") Integer numBoleta);
}
