package com.clinica.citas.controller;

import com.clinica.citas.model.dto.PacienteInputDTO;
import com.clinica.citas.model.dto.PacienteOutputDTO;
import com.clinica.citas.model.dto.PersonaDTO;
import com.clinica.citas.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@Log4j2
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/listar")
    public List<PersonaDTO> listarPersonas(@RequestHeader("Authorization") String authorization){
        return pacienteService.listarPersonas(authorization);
    }

    @PostMapping("/registrar")
    public PacienteOutputDTO registrarPaciente(
            @RequestHeader("Authorization") String authorization,
            @RequestBody PacienteInputDTO pacienteInput
    ){
        PersonaDTO persona = pacienteService.obtenerPersonaDNI(authorization, pacienteInput.dni());
        Double imc = pacienteService.calcularImc(pacienteInput.peso(), pacienteInput.talla());

        PacienteOutputDTO pacienteOutputDTO = new PacienteOutputDTO(
                persona,
                pacienteInput.peso(),
                pacienteInput.talla(),
                Math.round(imc * 100.0) / 100.0,
                pacienteInput.idEspecialidad()
        );

        pacienteService.publicarMensaje(pacienteOutputDTO);
        log.info("La cita del paciente fue registrada con exito!: " + pacienteOutputDTO);
        return pacienteOutputDTO;
    }
}
