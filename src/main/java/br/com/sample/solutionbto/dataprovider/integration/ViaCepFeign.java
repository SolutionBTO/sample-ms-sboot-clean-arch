package br.com.sample.solutionbto.dataprovider.integration;

import br.com.sample.solutionbto.dataprovider.integration.config.ViaCepConfiguration;
import br.com.sample.solutionbto.openapi.integration.api.ViaCepClientApi;
import br.com.sample.solutionbto.openapi.integration.model.EnderecoCompletoClientDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "via-cep", dismiss404 = true, configuration = { ViaCepConfiguration.class })
public interface ViaCepFeign  extends ViaCepClientApi {


    @GetMapping(
            value = ViaCepClientApi.PATH_CONSULTA_VIA_CEP,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    ResponseEntity<EnderecoCompletoClientDto> consultaViaCep(@PathVariable String cep);

    @GetMapping(
            value = ViaCepClientApi.PATH_PESQUISA_VIA_CEP_POR_ENDERECO,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    ResponseEntity<List<EnderecoCompletoClientDto>> pesquisaViaCepPorEndereco(
                                        @PathVariable String uf,
                                        @PathVariable String localidade,
                                        @PathVariable String logradouro);

}
