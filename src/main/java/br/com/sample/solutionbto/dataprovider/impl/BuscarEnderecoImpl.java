package br.com.sample.solutionbto.dataprovider.impl;

import br.com.sample.solutionbto.common.util.StringUtils;
import br.com.sample.solutionbto.core.dataprovider.BuscarEndereco;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.core.usecase.exception.ConsultaSemResultadoException;
import br.com.sample.solutionbto.dataprovider.mapper.EnderecoCompletoDocumentMapper;
import br.com.sample.solutionbto.dataprovider.integration.ViaCepFeign;
import br.com.sample.solutionbto.dataprovider.mapper.EnderecoCompletoDomainMapper;
import br.com.sample.solutionbto.dataprovider.mongodb.repository.EnderecoCompletoRepository;
import br.com.sample.solutionbto.openapi.integration.model.EnderecoCompletoClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarEnderecoImpl implements BuscarEndereco {

    private final EnderecoCompletoRepository repository;
    private final ViaCepFeign viaCepFeign;
    private final EnderecoCompletoDocumentMapper mapper;
    private final EnderecoCompletoDomainMapper mapperDomain;

    @Cacheable(value = "consultaCepCache", key = "#cep")
    @Override
    public EnderecoCompletoDomain consultaCep(String cep) {
        var enderecoDocument = repository.findByCep(cep);

        if (enderecoDocument.isPresent()) {
            return mapper.map(enderecoDocument.get());
        }

        var responseEntity = viaCepFeign.consultaViaCep(cep);

        if( responseEntity != null &&
                responseEntity.getBody() != null &&
                    responseEntity.getStatusCode().is2xxSuccessful() ) {
            verificaResponseComErro(responseEntity.getBody());
            var enderecoDocumentMap = mapper.map(responseEntity.getBody());
            var enderecoPersistido = repository.save(enderecoDocumentMap);
            return mapper.map(enderecoPersistido);
        }

        return null;
    }

    @Cacheable(value = "pesquisaCepPorEnderecoCache", key = "{#uf, #localidade, #logradouro}")
    @Override
    public List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {
        var listaEnderecos = repository.findByUfAndLocalidadeAndLogradouro(uf, localidade, logradouro);

        if(!listaEnderecos.isEmpty()){
            return mapper.mapListDomain(listaEnderecos);
        }

        var enderecoCompletosDto = viaCepFeign.pesquisaViaCepPorEndereco(
                uf,
                StringUtils.sanetizarTexto(localidade, true),
                StringUtils.sanetizarTexto(logradouro, true));

        return mapperDomain.mapListClientDtoToDomain(enderecoCompletosDto.getBody());
    }

    private static void verificaResponseComErro(EnderecoCompletoClientDto enderecoCompleto){
        if (enderecoCompleto.getErro() != null && enderecoCompleto.getErro()) {
            throw new ConsultaSemResultadoException();
        }
    }
}
