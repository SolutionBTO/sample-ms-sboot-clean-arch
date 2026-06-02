package br.com.sample.solutionbto.core.usecase.impl;

import br.com.sample.solutionbto.core.dataprovider.IncluirEndereco;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.core.usecase.IncluirEnderecoUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncluirEnderecoUsecaseImpl implements IncluirEnderecoUsecase {

    private final IncluirEndereco incluirEndereco;

    @Override
    public void persistir(EnderecoCompletoDomain enderecoCompletoDomain) {
        incluirEndereco.persistir(enderecoCompletoDomain);
    }
}
