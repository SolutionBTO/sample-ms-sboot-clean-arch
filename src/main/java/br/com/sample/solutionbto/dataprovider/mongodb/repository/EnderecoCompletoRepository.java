package br.com.sample.solutionbto.dataprovider.mongodb.repository;

import br.com.sample.solutionbto.dataprovider.mongodb.document.EnderecoCompletoDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnderecoCompletoRepository extends MongoRepository<EnderecoCompletoDocument, ObjectId> {

     EnderecoCompletoDocument findByCep(String cep);

     List<EnderecoCompletoDocument> findByUfAndLocalidadeAndLogradouro(String uf, String localidade, String logradouro);
}
