package com.mrjoi.messagejob.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="clientes")
@Getter
@Setter
public class Cliente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCliente;
	private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String  dni;
    private String telefono;
    private String correo;
    private String direccion;
    private String genero;
    private Date fechaNacimiento;
    private Long idLogin;
    private String rutaImg; 

}
