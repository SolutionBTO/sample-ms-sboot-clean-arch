package br.com.sample.solutionbto.dataprovider.integration.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder implements feign.codec.ErrorDecoder {

    private final feign.codec.ErrorDecoder defaultErrorDecoder = new feign.codec.ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        log.error("Erro na chamada ao serviço: {}", response.status());
        // Aqui você pode personalizar a lógica de decodificação de erros
        // Por exemplo, você pode verificar o status code e criar exceções específicas

        if (response.status() == 404) {
            return new RuntimeException("Recurso não encontrado");
        } else if (response.status() == 500) {
            return new RuntimeException("Erro interno do servidor");
        }
        // Para outros casos, use o decodificador padrão

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
