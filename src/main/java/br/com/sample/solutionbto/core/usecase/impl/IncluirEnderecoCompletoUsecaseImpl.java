package br.com.sample.solutionbto.core.usecase.impl;

import br.com.sample.solutionbto.core.dataprovider.IncluirEnderecoCompleto;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.core.usecase.IncluirEnderecoCompletoUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncluirEnderecoCompletoUsecaseImpl implements IncluirEnderecoCompletoUsecase {

    private final IncluirEnderecoCompleto incluirEnderecoCompleto;

    @Override
    public void persistir(EnderecoCompletoDomain enderecoCompletoDomain) {
        incluirEnderecoCompleto.persistir(enderecoCompletoDomain);
    }
}
