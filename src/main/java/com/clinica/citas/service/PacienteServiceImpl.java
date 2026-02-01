package com.clinica.citas.service;

import com.clinica.citas.client.IPersonaFeignClient;
import com.clinica.citas.contants.Constantes;
import com.clinica.citas.model.dto.PacienteOutputDTO;
import com.clinica.citas.model.dto.PersonaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements  PacienteService {

    private final IPersonaFeignClient personaService;
    private final KafkaTemplate<String, PacienteOutputDTO> kafkaTemplate;

    @Override
    public List<PersonaDTO> listarPersonas() {
        return personaService.listarPersonas();
    }

    @Override
    public Double calcularImc(Double peso, Double talla) {
        return peso / Math.pow(talla, 2);
    }

    @Override
    public PersonaDTO obtenerPersonaDNI(String dni) {
        return personaService.personaPorDni(dni);
    }

    @Override
    public void publicarMensaje(PacienteOutputDTO pacienteOutputDTO) {
        kafkaTemplate.send(Constantes.TOPICO, pacienteOutputDTO);
    }
}
