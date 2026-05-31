package br.com.sample.solutionbto.core.usecase;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;

import java.util.List;

public interface ConsultaCepUsecase {

    EnderecoCompletoDomain consultaCep(String cep);

    List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro);
}
