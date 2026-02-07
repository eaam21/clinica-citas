package com.clinica.citas.client;

import com.clinica.citas.model.dto.PersonaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name="persona", url="localhost:8080")
@FeignClient(name="personas")
public interface IPersonaFeignClient {
    @GetMapping("/personas")
    List<PersonaDTO> listarPersonas();

    @GetMapping("/personas/dni/{dni}")
    PersonaDTO personaPorDni(@PathVariable("dni") String dni);
}
