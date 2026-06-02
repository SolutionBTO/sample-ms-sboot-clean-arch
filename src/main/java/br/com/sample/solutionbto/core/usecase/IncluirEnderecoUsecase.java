package br.com.sample.solutionbto.core.usecase;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;

public interface IncluirEnderecoUsecase {

    void persistir(EnderecoCompletoDomain enderecoCompletoDomain);
}
