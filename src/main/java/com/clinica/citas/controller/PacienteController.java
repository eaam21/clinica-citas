package com.clinica.citas.controller;

import com.clinica.citas.model.dto.PacienteInputDTO;
import com.clinica.citas.model.dto.PacienteOutputDTO;
import com.clinica.citas.model.dto.PersonaDTO;
import com.clinica.citas.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/listar")
    public List<PersonaDTO> listarPersonas(){
        return pacienteService.listarPersonas();
    }

    @PostMapping("/registrar")
    public PacienteOutputDTO registrarPaciente(@RequestBody PacienteInputDTO pacienteInput){
        PersonaDTO persona = pacienteService.obtenerPersonaDNI(pacienteInput.dni());
        Double imc = pacienteService.calcularImc(pacienteInput.peso(), pacienteInput.talla());
        return new PacienteOutputDTO(persona, pacienteInput.peso(), pacienteInput.talla(), Math.round(imc * 100.0) / 100.0, pacienteInput.idEspecialidad());
    }
}
