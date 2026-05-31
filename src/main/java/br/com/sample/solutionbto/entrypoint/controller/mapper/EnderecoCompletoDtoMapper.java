package br.com.sample.solutionbto.entrypoint.controller.mapper;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.openapi.model.EnderecoCompleto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoCompletoDtoMapper {

    EnderecoCompleto map(EnderecoCompletoDomain enderecos);

    List<EnderecoCompleto> map(List<EnderecoCompletoDomain> listaEnderecos);
}
