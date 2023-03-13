package com.mrjoi.messagejob.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="detalles_boleta")
@Getter
@Setter
public class DetalleBoleta {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idDetalle;
	@Column(name="numBoleta")
	private int numBoleta;
	@Column(name = "id_tipo_entrada")
	private int idTipoEntrada;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "precio_unitario")
	private double precioUnitario;

}
