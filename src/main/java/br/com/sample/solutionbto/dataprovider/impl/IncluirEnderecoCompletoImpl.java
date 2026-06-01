package br.com.sample.solutionbto.dataprovider.impl;

import br.com.sample.solutionbto.core.dataprovider.IncluirEnderecoCompleto;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.dataprovider.mapper.EnderecoCompletoDocumentMapper;
import br.com.sample.solutionbto.dataprovider.mongodb.repository.EnderecoCompletoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncluirEnderecoCompletoImpl implements IncluirEnderecoCompleto {

    private final EnderecoCompletoRepository repository;
    private final EnderecoCompletoDocumentMapper mapper;


    @Override
    public void persistir(EnderecoCompletoDomain enderecoCompletoDomain) {
        var document = mapper.map(enderecoCompletoDomain);
        repository.save(document);
    }
}
