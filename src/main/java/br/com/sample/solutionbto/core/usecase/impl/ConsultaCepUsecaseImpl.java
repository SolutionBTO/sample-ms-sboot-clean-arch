package br.com.sample.solutionbto.core.usecase.impl;

import br.com.sample.solutionbto.core.dataprovider.ConsultaViaCep;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.core.usecase.ConsultaCepUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaCepUsecaseImpl implements ConsultaCepUsecase {

    private final ConsultaViaCep consultaViaCep;

    @Override
    public EnderecoCompletoDomain consultaCep(String cep) {
        return consultaViaCep.consultaCep(cep);
    }

    @Override
    public List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {
        return consultaViaCep.pesquisaCepPorEndereco(uf, localidade, logradouro);
    }
}
