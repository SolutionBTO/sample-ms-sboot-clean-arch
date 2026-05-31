package br.com.sample.solutionbto.core.dataprovider;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;

import java.util.List;

public interface ConsultaViaCep {

    EnderecoCompletoDomain consultaCep(String cep);

    List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro);
}
