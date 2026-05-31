package br.com.sample.solutionbto.dataprovider.mapper;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.openapi.integration.model.EnderecoCompletoClientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoCompletoDomainMapper {

    EnderecoCompletoDomain map(EnderecoCompletoClientDto enderecos);

    List<EnderecoCompletoDomain> map(List<EnderecoCompletoClientDto> listaEnderecos);
}
