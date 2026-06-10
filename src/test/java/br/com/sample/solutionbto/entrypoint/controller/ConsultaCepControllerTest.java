package br.com.sample.solutionbto.entrypoint.controller;

import br.com.sample.solutionbto.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
@ContextConfiguration(classes = App.class)
@EnableWireMock({ // TODO mudar lib!
        @ConfigureWireMock(
                name = "viacep",
                port = 0),
})
@ActiveProfiles("test")
@DisplayName("ConsultaCepController")
class ConsultaCepControllerTest {

    @InjectWireMock("viacep")
    WireMockServer mockCepService;

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api/v1";
        RestAssured.urlEncodingEnabled = true;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    @Test
    @DisplayName("Consulta de CEP - Sucesso")
    void consultaCep_Sucesso() throws IOException, JSONException {
        String cep = "01001000";

        stubFor(get(urlEqualTo(String.format("/ws/%s/json", cep)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("response-consulta-cep.json")));


        String responseRetornado = callRestAssurence("/consulta-cep/{cep}", Map.of("cep", cep));
        log.info("responseRetornado: {}", responseRetornado);
        var responseEsperado =  FileUtils.readFileToString(ResourceUtils.getFile("classpath:json/response-consulta-cep-esperado.json"), "UTF-8");
        log.info("responseEsperado: {}", responseEsperado);

        JSONAssert.assertEquals(responseEsperado, responseRetornado, false);
    }

    @Test
    @DisplayName("Consulta de CEP - Não Encontrado")
    void consultaCep_NaoEncontrado() throws IOException, JSONException {
        String cep = "99999999";

        stubFor(get(urlEqualTo(String.format("/ws/%s/json", cep)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("response-erro-consulta-cep.json")));


        String responseRetornado = callRestAssurence("/consulta-cep/{cep}", Map.of("cep", cep), 404, true);
        log.info("responseRetornado: {}", responseRetornado);
        var responseEsperado =  FileUtils.readFileToString(ResourceUtils.getFile("classpath:json/response-404-consulta-cep-esperado.json"), "UTF-8");
        log.info("responseEsperado: {}", responseEsperado);

        JSONAssert.assertEquals(responseEsperado, responseRetornado, false);
    }

    // TODO criar restantes dos testes

    private static String callRestAssurence(String url, Map<String, Object> params){
        return callRestAssurence(url, params, 200, true);
    }

    private static String callRestAssurence(String url, Map<String, Object> params, int httpStatus ,boolean isPathParam){
        //given() → [set parameters/headers] → .when() → [perform action] → .then() → [assertions]
        var header = given()
                        .noContentType()
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .log().all(true);

        if(isPathParam)
            header.pathParams(params);
        else
            header.params(params);

        return header.when()
                .get(url)
                .then()
                .statusCode(httpStatus)
                .extract()
                .body().asString();
    }
}




