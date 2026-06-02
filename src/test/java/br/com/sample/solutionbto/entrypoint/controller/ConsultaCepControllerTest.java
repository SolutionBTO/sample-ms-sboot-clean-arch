/*package br.com.sample.solutionbto.entrypoint.controller;

import br.com.sample.solutionbto.core.domain.EnderecoCompletoDomain;
import br.com.sample.solutionbto.core.usecase.BuscarEnderecoUsecase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("ConsultaCepController")
class ConsultaCepControllerTest {

    @LocalServerPort
    private int port;

    @MockitoBean
    private BuscarEnderecoUsecase buscarEnderecoUsecase;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1";
    }

    @Test
    @DisplayName("should return address when CEP is found")
    void returnAddressWhenCepIsFound() {
        String cep = "01001000";
        EnderecoCompletoDomain endereco = EnderecoCompletoDomain.builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .complemento("lado ímpar")
                .unidade("")
                .bairro("Sé")
                .localidade("São Paulo")
                .uf("SP")
                .estado("São Paulo")
                .regiao("Sudeste")
                .ibge("3550308")
                .gia("1004")
                .ddd("11")
                .siafi("7107")
                .erro(false)
                .build();

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(endereco);

        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("cep", cep)
                .when()
                .get("/consulta-cep/{cep}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("cep", equalTo("01001-000"))
                .body("logradouro", equalTo("Praça da Sé"))
                .body("localidade", equalTo("São Paulo"))
                .body("uf", equalTo("SP"));
    }

    @Test
    @DisplayName("should return no content when CEP is not found with error flag")
    void returnNoContentWhenCepNotFoundWithErrorFlag() {
        String cep = "99999999";
        EnderecoCompletoDomain endereco = EnderecoCompletoDomain.builder()
                .cep("99999-999")
                .erro(true)
                .build();

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(endereco);

        given()
                .when()
                .pathParam("cep", cep)
                .get("/consulta-cep/{cep}")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("should return no content when usecase returns null")
    void returnNoContentWhenUsecaseReturnsNull() {
        String cep = "00000000";

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(null);

        given()
                .when()
                .pathParam("cep", cep)
                .get("/consulta-cep/{cep}")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("should return no content when erro field is null and treated as false")
    void returnAddressWhenErroFieldIsNull() {
        String cep = "12345678";
        EnderecoCompletoDomain endereco = EnderecoCompletoDomain.builder()
                .cep("12345-678")
                .logradouro("Rua Teste")
                .localidade("São Paulo")
                .uf("SP")
                .erro(null)
                .build();

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(endereco);

        given()
                .when()
                .pathParam("cep", cep)
                .get("/consulta-cep/{cep}")
                .then()
                .statusCode(200)
                .body("logradouro", equalTo("Rua Teste"));
    }

    @Test
    @DisplayName("should return list of addresses when searching by address parameters")
    void returnListOfAddressesWhenSearchingByAddressParameters() {
        String uf = "SP";
        String localidade = "São Paulo";
        String logradouro = "Avenida Paulista";

        List<EnderecoCompletoDomain> enderecos = List.of(
                EnderecoCompletoDomain.builder()
                        .cep("01311-100")
                        .logradouro("Avenida Paulista")
                        .localidade("São Paulo")
                        .uf("SP")
                        .bairro("Bela Vista")
                        .build(),
                EnderecoCompletoDomain.builder()
                        .cep("01311-200")
                        .logradouro("Avenida Paulista")
                        .localidade("São Paulo")
                        .uf("SP")
                        .bairro("Consolação")
                        .build()
        );

        when(buscarEnderecoUsecase.pesquisaCepPorEndereco(uf, localidade, logradouro))
                .thenReturn(enderecos);

        given()
                .when()
                .get("/consulta-cep/uf/{uf}/municipio/{localidade}/logradouro/{logradouro}",
                        uf, localidade, logradouro)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(2))
                .body("[0].cep", equalTo("01311-100"))
                .body("[1].cep", equalTo("01311-200"))
                .body("[0].logradouro", equalTo("Avenida Paulista"))
                .body("[1].logradouro", equalTo("Avenida Paulista"));
    }

    @Test
    @DisplayName("should return no content when no addresses are found")
    void returnNoContentWhenNoAddressesAreFound() {
        String uf = "XX";
        String localidade = "CidadeInexistente";
        String logradouro = "RuaInexistente";

        when(buscarEnderecoUsecase.pesquisaCepPorEndereco(uf, localidade, logradouro))
                .thenReturn(Collections.emptyList());

        given()
                .when()
                .get("/consulta-cep/uf/{uf}/municipio/{localidade}/logradouro/{logradouro}",
                        uf, localidade, logradouro)
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("should return multiple addresses with all expected fields populated")
    void returnMultipleAddressesWithAllExpectedFields() {
        String uf = "RJ";
        String localidade = "Rio de Janeiro";
        String logradouro = "Avenida Brasil";

        List<EnderecoCompletoDomain> enderecos = List.of(
                EnderecoCompletoDomain.builder()
                        .cep("20000-000")
                        .logradouro("Avenida Brasil")
                        .complemento("esquina com Rua A")
                        .unidade("")
                        .bairro("Centro")
                        .localidade("Rio de Janeiro")
                        .uf("RJ")
                        .estado("Rio de Janeiro")
                        .regiao("Sudeste")
                        .ibge("3304557")
                        .gia("")
                        .ddd("21")
                        .siafi("5005")
                        .build()
        );

        when(buscarEnderecoUsecase.pesquisaCepPorEndereco(uf, localidade, logradouro))
                .thenReturn(enderecos);

        given()
                .when()
                .get("/consulta-cep/uf/{uf}/municipio/{localidade}/logradouro/{logradouro}",
                        uf, localidade, logradouro)
                .then()
                .statusCode(200)
                .body("size()", equalTo(1))
                .body("[0].cep", equalTo("20000-000"))
                .body("[0].logradouro", equalTo("Avenida Brasil"))
                .body("[0].bairro", equalTo("Centro"))
                .body("[0].localidade", equalTo("Rio de Janeiro"))
                .body("[0].uf", equalTo("RJ"))
                .body("[0].ddd", equalTo("21"));
    }

    @Test
    @DisplayName("should handle single address in search results")
    void handleSingleAddressInSearchResults() {
        String uf = "MG";
        String localidade = "Belo Horizonte";
        String logradouro = "Avenida Olegário Maciel";

        List<EnderecoCompletoDomain> enderecos = List.of(
                EnderecoCompletoDomain.builder()
                        .cep("30140-110")
                        .logradouro("Avenida Olegário Maciel")
                        .localidade("Belo Horizonte")
                        .uf("MG")
                        .bairro("Savassi")
                        .build()
        );

        when(buscarEnderecoUsecase.pesquisaCepPorEndereco(uf, localidade, logradouro))
                .thenReturn(enderecos);

        given()
                .when()
                .get("/consulta-cep/uf/{uf}/municipio/{localidade}/logradouro/{logradouro}",
                        uf, localidade, logradouro)
                .then()
                .statusCode(200)
                .body("size()", equalTo(1))
                .body("[0].cep", equalTo("30140-110"));
    }

    @Test
    @DisplayName("should return complete address with all fields including optional ones")
    void returnCompleteAddressWithAllFields() {
        String cep = "08020000";
        EnderecoCompletoDomain endereco = EnderecoCompletoDomain.builder()
                .cep("08020-000")
                .logradouro("Avenida do Estado")
                .complemento("")
                .unidade("")
                .bairro("Belém")
                .localidade("São Paulo")
                .uf("SP")
                .estado("São Paulo")
                .regiao("Sudeste")
                .ibge("3550308")
                .gia("1004")
                .ddd("11")
                .siafi("7107")
                .erro(false)
                .build();

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(endereco);

        given()
                .when()
                .get("/consulta-cep/{cep}", cep)
                .then()
                .statusCode(200)
                .body("cep", equalTo("08020-000"))
                .body("logradouro", equalTo("Avenida do Estado"))
                .body("bairro", equalTo("Belém"))
                .body("localidade", equalTo("São Paulo"))
                .body("uf", equalTo("SP"))
                .body("estado", equalTo("São Paulo"))
                .body("regiao", equalTo("Sudeste"))
                .body("ibge", equalTo("3550308"))
                .body("gia", equalTo("1004"))
                .body("ddd", equalTo("11"))
                .body("siafi", equalTo("7107"))
                .body("erro", equalTo(false));
    }

    @Test
    @DisplayName("should handle address with error flag set to true")
    void handleAddressWithErrorFlagSetToTrue() {
        String cep = "11111111";
        EnderecoCompletoDomain endereco = EnderecoCompletoDomain.builder()
                .cep("11111-111")
                .erro(true)
                .build();

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(endereco);

        given()
                .when()
                .get("/consulta-cep/{cep}", cep)
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("should return content type as JSON")
    void returnContentTypeAsJson() {
        String cep = "01001000";
        EnderecoCompletoDomain endereco = EnderecoCompletoDomain.builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .localidade("São Paulo")
                .uf("SP")
                .erro(false)
                .build();

        when(buscarEnderecoUsecase.consultaCep(cep)).thenReturn(endereco);

        given()
                .when()
                .get("/consulta-cep/{cep}", cep)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("should return status 200 and content type JSON for search results")
    void returnStatus200AndContentTypeJsonForSearchResults() {
        String uf = "SP";
        String localidade = "São Paulo";
        String logradouro = "Rua Augusta";

        List<EnderecoCompletoDomain> enderecos = List.of(
                EnderecoCompletoDomain.builder()
                        .cep("01305-100")
                        .logradouro("Rua Augusta")
                        .localidade("São Paulo")
                        .uf("SP")
                        .build()
        );

        when(buscarEnderecoUsecase.pesquisaCepPorEndereco(uf, localidade, logradouro))
                .thenReturn(enderecos);

        given()
                .when()
                .get("/consulta-cep/uf/{uf}/municipio/{localidade}/logradouro/{logradouro}",
                        uf, localidade, logradouro)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}*/





