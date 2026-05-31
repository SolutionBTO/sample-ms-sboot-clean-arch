package br.com.sample.solutionbto.dataprovider.mapper;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.openapi.model.EnderecoCompleto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoCompletoDomainMapper {

    EnderecoCompletoDomain map(EnderecoCompleto enderecos);

    List<EnderecoCompletoDomain> map(List<EnderecoCompleto> listaEnderecos);
}
