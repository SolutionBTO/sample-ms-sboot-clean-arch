package br.com.sample.solutionbto.dataprovider.mongodb.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "ENDERECO_COMPLETO")
public class EnderecoCompletoDocument {

    @Id
    @Field("_id")
    private ObjectId id;

    private String cep;

    private String logradouro;

    private String complemento;

    private String unidade;

    private String bairro;

    private String localidade;

    private String uf;

    private String estado;

    private String regiao;

    private String ibge;

    private String gia;

    private String ddd;

    private String siafi;

    private Boolean erro;

    @CreatedDate
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @TextScore
    @Field("text_score")
    private Float textScore;

    @Version
    @Field("version")
    private Long version;
}
