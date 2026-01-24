package com.clinica.citas.model.dto;

import java.util.Date;

public record PersonaDTO(
        Long idPersona,
        String nombres,
        String apellidoPaterno,
        String apellidoMaterno,
        String dni,
        Date fechaNacimiento,
        String direccion,
        String telefono,
        EstadoCivilDTO estadoCivil,
        GeneroDTO genero
) {
}
