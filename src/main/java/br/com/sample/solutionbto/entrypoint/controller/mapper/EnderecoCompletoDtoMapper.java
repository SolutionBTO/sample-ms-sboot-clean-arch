package br.com.sample.solutionbto.entrypoint.controller.mapper;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.openapi.model.EnderecoCompletoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoCompletoDtoMapper {

    EnderecoCompletoDto map(EnderecoCompletoDomain enderecos);

    List<EnderecoCompletoDto> map(List<EnderecoCompletoDomain> listaEnderecos);
}
