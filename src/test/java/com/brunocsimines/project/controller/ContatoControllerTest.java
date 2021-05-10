package com.brunocsimines.project.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import com.brunocsimines.project.domain.Contato;
import com.brunocsimines.project.exceptions.EmailInvalidoException;
import com.brunocsimines.project.service.ContatoService;
import com.brunocsimines.project.service.PessoaService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.mock.*;

@WebMvcTest
public class ContatoControllerTest {

    @Autowired
    private ContatoController contatoController;

    @MockBean
    private ContatoService contatoService;

    @MockBean
    private PessoaService pessoaService;

    @BeforeEach
    public void setup() {
       standaloneSetup(this.contatoController);
    }

    @Test
    public void deveRetornarCreated_QuandoCriarContato() throws Exception {
        Contato contato = new Contato();
        contato.setNome("Bruno Teste");
        contato.setEmail("bruno.simines@hotmail.com");
        contato.setTelefone("3323-6143");

        Mockito.when(this.contatoService.salvar(contato)).thenReturn(contato);

        given()
                .accept(ContentType.JSON)
        .when()
                .post("/contato")
        .then()
                .statusCode(201);
    }

    @Test
    public void deveRetornarOK_QuandoBuscarContatoPorId() throws EmailInvalidoException {
        Contato contato = new Contato();
        contato.setNome("Bruno Teste");
        contato.setEmail("bruno.simines@hotmail.com");
        contato.setTelefone("3323-6143");

        Mockito.when(this.contatoService.buscarPorId(1L)).thenReturn(contato);

        given()
                .accept(ContentType.JSON)
        .when()
                .get("/contato/{id}", 1L)
        .then()
                .statusCode(200);

    }

    @Test
    public void deveRetornarOK_QuandoDeletarContatoPorId() throws EmailInvalidoException {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/contato")
                .then()
                .statusCode(200);

    }


}
