package com.clinica.citas.model.dto;

public record PacienteInputDTO(
        String dni,
        Double peso,
        Double talla,
        Integer idEspecialidad
) {
}
