package br.com.sample.solutionbto.dataprovider.integration.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ViaCepConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
