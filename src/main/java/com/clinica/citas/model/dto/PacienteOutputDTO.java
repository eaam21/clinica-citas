package com.clinica.citas.model.dto;

public record PacienteOutputDTO(
        PersonaDTO personaDTO,
        Double imc
) {
}
