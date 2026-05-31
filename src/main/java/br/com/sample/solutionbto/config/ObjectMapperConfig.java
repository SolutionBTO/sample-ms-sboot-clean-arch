package br.com.sample.solutionbto.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registrar módulo para suportar datas/horas Java 8+
        mapper.registerModule(new JavaTimeModule());
        // Não incluir valores nulos na serialização
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        // Ignorar campos desconhecidos durante desserialização
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // Não escrever datas como timestamp, usar formato ISO-8601
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Tentar ler valores enum desconhecidos como null ao invés de falhar
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        // Aceitar valores simples como arrays (resiliência)
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        // Usar valores padrão para construtores ao invés de falhar
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        return mapper;
    }
}
