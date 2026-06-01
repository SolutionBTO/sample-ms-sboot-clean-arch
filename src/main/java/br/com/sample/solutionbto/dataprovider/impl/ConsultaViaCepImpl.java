package br.com.sample.solutionbto.dataprovider.impl;

import br.com.sample.solutionbto.common.util.StringUtils;
import br.com.sample.solutionbto.core.dataprovider.ConsultaViaCep;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.dataprovider.mapper.EnderecoCompletoDomainMapper;
import br.com.sample.solutionbto.dataprovider.integration.ViaCepFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultaViaCepImpl implements ConsultaViaCep {

    private final ViaCepFeign viaCepFeign;
    private final EnderecoCompletoDomainMapper mapper;

    @Cacheable(value = "consultaCepCache", key = "#cep")
    @Override
    public EnderecoCompletoDomain consultaCep(String cep) {
        var enderecoCompletoDto = viaCepFeign.consultaViaCep(cep);
        return mapper.map(enderecoCompletoDto.getBody());
    }

    @Cacheable(value = "pesquisaCepPorEnderecoCache", key = "{#uf, #localidade, #logradouro}")
    @Override
    public List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {
        var enderecoCompletosDto = viaCepFeign.pesquisaViaCepPorEndereco(
                uf,
                StringUtils.sanetizarTexto(localidade, true),
                StringUtils.sanetizarTexto(logradouro, true));

        return mapper.map(enderecoCompletosDto.getBody());
    }
}
