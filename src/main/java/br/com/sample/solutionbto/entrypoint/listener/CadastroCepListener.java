package br.com.sample.solutionbto.entrypoint.listener;

import br.com.sample.solutionbto.core.usecase.IncluirEnderecoUsecase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class CadastroCepListener {

    private final ObjectMapper objectMapper;
    private final IncluirEnderecoUsecase usecase;

    @RabbitListener(queues = "${app.rabbitmq.queue-name}", concurrency = "1-5")
    public void receiveMessage(Message message) throws JsonProcessingException {
        // Process the received message (e.g., save to database, call another service, etc.)
        var messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("messageId: {}", message.getMessageProperties().getMessageId());
        log.info("consumerQueue: {}", message.getMessageProperties().getConsumerQueue());
        log.info("Received message: {}", messageBody);

        usecase.persistir(objectMapper.readValue(messageBody, br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain.class));
    }
}
