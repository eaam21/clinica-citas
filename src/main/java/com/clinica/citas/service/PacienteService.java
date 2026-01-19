package com.clinica.citas.service;

import com.clinica.citas.model.dto.PersonaDTO;

import java.util.List;

public interface PacienteService {
    List<PersonaDTO> listarPersonas();
    Double calcularImc(Double peso, Double talla);
    PersonaDTO obtenerPersonaDNI(String dni);
}
