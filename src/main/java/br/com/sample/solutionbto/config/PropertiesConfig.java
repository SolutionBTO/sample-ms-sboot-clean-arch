package br.com.sample.solutionbto.config;

import br.com.sample.solutionbto.common.properties.RabbitMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Bean
    RabbitMQProperties rabbitMQProperties() {
        return new RabbitMQProperties();
    }
}
