package com.mrjoi.messagejob.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReserva;
    @Column(name="fecha_registro")
    private Date fechaRegistro;
    @Column(name="fecha_reserva")
    private Date fechaReserva;
    @Column(name="hora")
    private String hora;
    @Column(name="cant_personas")
    private int cantPersonas;
    @Column(name="acompaniante")
    private int acompaniante;
    @Column(name="nombres")
    private String nombres;
    @Column(name="apellido")
    private String apellido;
    @Column(name="telefono")
    private String telefono;
    @Column(name="email")
    private String email;
    @Column(name="dni")
    private String dni;
    @Column(name="has_email")
    private Boolean hasEmail;
    @Column(name="flag_tipo_reserva")
    private String flagTipoReserva;
    @Column(name="total_pago")
    private double totalPago;
    @Column(name="estado")
    private String estado;
    @Column(name="id_paquete")
    private int idPaquete;
    @Column(name="id_login")
    private int idLogin;
    @Column(name="motivo_anulacion")
    private String motivoAnulacion;
    @Column(name="diferencia_pagar")
    private double diferenciaPagar;
    @Column(name="fecha_modifacion")
    private Date fechaModificacion;
    @Column(name="usuario_modifacion")
    private String usuarioModificacion;

}