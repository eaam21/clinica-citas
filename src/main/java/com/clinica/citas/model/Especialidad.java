package com.clinica.citas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_especialidad")
@Data
public class Especialidad  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
	private Long idEspecialidad;
    private  String nombre;
}