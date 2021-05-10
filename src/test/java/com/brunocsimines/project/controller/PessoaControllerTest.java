package com.brunocsimines.project.controller;

import com.brunocsimines.project.domain.Contato;
import com.brunocsimines.project.domain.Pessoas;
import com.brunocsimines.project.exceptions.EmailInvalidoException;
import com.brunocsimines.project.service.ContatoService;
import com.brunocsimines.project.service.PessoaService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class PessoaControllerTest {

    @Autowired
    private PessoaController pessoaController;

    @MockBean
    private PessoaService pessoaService;

    @MockBean
    private ContatoService contatoService;


    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.pessoaController);
    }

    @Test
    public void deveRetornarOk_quandoBuscarPessoaPorCpf() throws EmailInvalidoException {
        String cpf = "40935919899";
        Contato contato = new Contato();
        contato.setTelefone("33233333");
        contato.setNome("Bruno C. Simines");
        contato.setEmail("bruno.simines@hotmail.com");
        List<Contato> list = new ArrayList<>();
        list.add(contato);

        Pessoas pessoas = new Pessoas();
        pessoas.setNome("Teste");
        pessoas.setCpf("40935919899");
        pessoas.setEmail("bruno.simines@hotmail.com");
        pessoas.setContatos(list);

        Mockito.when(this.pessoaService.buscarPorCpf(cpf)).thenReturn(pessoas);

        given()
                .accept(ContentType.JSON)
        .when()
                .get("/pessoa/",cpf)
        .then()
                .statusCode(200);
    }
}
