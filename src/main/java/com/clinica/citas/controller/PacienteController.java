package com.clinica.citas.controller;

import com.clinica.citas.configuration.MQConfig;
import com.clinica.citas.model.dto.PacienteInputDTO;
import com.clinica.citas.model.dto.PacienteOutputDTO;
import com.clinica.citas.model.dto.PersonaDTO;
import com.clinica.citas.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@Log4j2
public class PacienteController {

    private final PacienteService pacienteService;
    private final RabbitTemplate template;

    @GetMapping("/listar")
    public List<PersonaDTO> listarPersonas(){
        return pacienteService.listarPersonas();
    }

    @PostMapping("/registrar")
    public PacienteOutputDTO registrarPaciente(@RequestBody PacienteInputDTO pacienteInput){
        PersonaDTO persona = pacienteService.obtenerPersonaDNI(pacienteInput.dni());
        Double imc = pacienteService.calcularImc(pacienteInput.peso(), pacienteInput.talla());
        PacienteOutputDTO pacienteOutputDTO = new PacienteOutputDTO(persona, pacienteInput.peso(), pacienteInput.talla(), Math.round(imc * 100.0) / 100.0, pacienteInput.idEspecialidad());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, pacienteOutputDTO);
        log.info("La cita del paciente fue registrada con exito!: "+pacienteOutputDTO);
        return pacienteOutputDTO;
    }
}
