package br.com.sample.solutionbto.dataprovider.integration;

import br.com.sample.solutionbto.dataprovider.integration.config.ViaCepConfiguration;
import br.com.sample.solutionbto.openapi.model.EnderecoCompleto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "via-cep", dismiss404 = true, configuration = ViaCepConfiguration.class)
public interface ViaCepFeign  {


    @GetMapping(
            value = "/{cep}/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    EnderecoCompleto consultaViaCep(@PathVariable String cep);

    @GetMapping(
            value = "/{uf}/{localidade}/{logradouro}/json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<EnderecoCompleto> pesquisaViaCepPorEndereco(@PathVariable String uf, @PathVariable String localidade, @PathVariable String logradouro);
}
