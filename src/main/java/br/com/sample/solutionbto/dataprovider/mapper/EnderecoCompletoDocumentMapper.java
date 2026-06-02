package br.com.sample.solutionbto.dataprovider.mapper;

import br.com.sample.solutionbto.common.util.StringUtils;
import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.dataprovider.mongodb.document.EnderecoCompletoDocument;
import br.com.sample.solutionbto.openapi.integration.model.EnderecoCompletoClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoCompletoDocumentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao" , ignore = true)
    @Mapping(target = "dataAtualizacao" , ignore = true)
    @Mapping(target = "textScore" , ignore = true)
    @Mapping(target = "version" , ignore = true)
    EnderecoCompletoDocument map(EnderecoCompletoDomain domain);

    EnderecoCompletoDomain map( EnderecoCompletoDocument document);

    List<EnderecoCompletoDomain> mapListDomain(List<EnderecoCompletoDocument> listaEnderecos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao" , ignore = true)
    @Mapping(target = "dataAtualizacao" , ignore = true)
    @Mapping(target = "textScore" , ignore = true)
    @Mapping(target = "version" , ignore = true)
    @Mapping(target = "cep", source = "dto.cep", qualifiedByName = "mapSanetizarCep")
    EnderecoCompletoDocument map(EnderecoCompletoClientDto dto);

    @Named("mapSanetizarCep")
    default String mapSanetizarCep(String cep) {
        if (cep != null) {
            return StringUtils.sanetizarTexto(cep);
        }
        return null;
    }
}
