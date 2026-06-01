package br.com.sample.solutionbto.config;

import br.com.sample.solutionbto.common.properties.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.rabbitmq.local-enabled", havingValue = "true")
public class RabbitMQLocalConfig {

    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(rabbitMQProperties.getExchangeName(), true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(rabbitMQProperties.getQueueName(), true);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange)
                .with(rabbitMQProperties.getRoutingKey());
    }
}
