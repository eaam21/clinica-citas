package com.clinica.citas.model.dto;

public record PacienteOutputDTO(
        PersonaDTO personaDTO,
        Double peso,
        Double talla,
        Double imc,
        Integer idEspecialidad
) {
}
