package com.mrjoi.messagejob.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="boleto_entrada")
@Getter
@Setter
public class BoletaEntrada {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int numBoleta;
	
	@Column(name="fechaRegistro")
	private Date fechaRegistro;
	
	@Column(name="total")
	private Double total;
	
	@Column(name="idLogin")
	private int idLogin;

	@Column(name="flagTipoBoleta",nullable = false, length = 1)
	private String flagTipoBoleta;

	@Column(name="has_email",nullable = false)
	private Boolean hasEmail = false;

}