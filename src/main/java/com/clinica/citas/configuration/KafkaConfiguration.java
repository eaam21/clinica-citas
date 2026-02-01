package com.clinica.citas.configuration;


import com.clinica.citas.contants.Constantes;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic(Constantes.TOPICO, 1, (short) 1);
    }
}