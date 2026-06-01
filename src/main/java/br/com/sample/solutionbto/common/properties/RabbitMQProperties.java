package br.com.sample.solutionbto.common.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.rabbitmq")
public class RabbitMQProperties {

    @NotNull
    private Boolean localEnabled;

    @NotNull
    private String exchangeName;

    @NotNull
    private String queueName;

    @NotNull
    private String routingKey;
}
