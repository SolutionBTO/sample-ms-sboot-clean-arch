package br.com.sample.solutionbto.core.usecase.impl;

import br.com.sample.solutionbto.core.dataprovider.BuscarEndereco;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.core.usecase.BuscarEnderecoUsecase;
import br.com.sample.solutionbto.core.usecase.exception.PesquisaPorEnderecoIlegalArgsException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarEnderecoUsecaseImpl implements BuscarEnderecoUsecase {

    private final BuscarEndereco buscarEndereco;

    @Override
    public EnderecoCompletoDomain consultaCep(String cep) {
        return buscarEndereco.consultaCep(cep);
    }

    @Override
    public List<EnderecoCompletoDomain> pesquisaCepPorEndereco(String uf, String localidade, String logradouro) {

        if(StringUtils.isAnyBlank(uf, localidade, logradouro)){
            throw new PesquisaPorEnderecoIlegalArgsException();
        }

        return buscarEndereco.pesquisaCepPorEndereco(uf, localidade, logradouro);
    }
}
