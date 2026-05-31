package br.com.sample.solutionbto.dataprovider.mapper;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.dataprovider.mongodb.document.EnderecoCompletoDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnderecoCompletoDocumentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao" , ignore = true)
    @Mapping(target = "dataAtualizacao" , ignore = true)
    @Mapping(target = "textScore" , ignore = true)
    @Mapping(target = "version" , ignore = true)
    EnderecoCompletoDocument mapToDocument(EnderecoCompletoDomain domain);
}
