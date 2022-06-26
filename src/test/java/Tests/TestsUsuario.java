package Tests;

import API.AcessoAPI;
import API.CriaUsuario;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class TestsUsuario extends  AcessoAPI{

    private static final String LISTA_USUARIOS_ENPOINT ="/users";
    private static final String CRIA_USUARIO_ENPOINT ="/user";
    private static final String CONSULTA_USUARIO_ENPOINT ="/users/{userId}";

    @Test
    public void testeCriaUsuario(){
        CriaUsuario usuario = new CriaUsuario("edson","QA");
        given().
                contentType(ContentType.JSON).
                body(usuario).
        when().
                post(CRIA_USUARIO_ENPOINT).
        then().
                statusCode(201).
                body("name", is("edson"));
    }

    @Test
    public void testeConsultaUmUsuario(){
        given().
                pathParam("userId", "3").
        when().
                get(CONSULTA_USUARIO_ENPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                body("data.first_name", is("Emma"));
    }

    @Test
    public void testeListaDadosUsuariosPaginaEspecifica() {
        given().
                params("page", "2").
        when().
                get(LISTA_USUARIOS_ENPOINT).
        then().
                statusCode(200).
                body("page", is(2),
                "data", is(notNullValue()));

    }

    @Test
    public void testeListaDadosUsuariosPaginaIgualAoPerPage() {
        int paginaSolicitada = 1;
        int perPageEsperado = retornaPerPageEsperado(paginaSolicitada);

        given().
                params("page", paginaSolicitada).
        when().
                get(LISTA_USUARIOS_ENPOINT).
        then().
                statusCode(200).
                body(
                        "page", is(paginaSolicitada),
                        "data.size()", is(perPageEsperado),
                        "data.findAll {it.avatar.startsWith('https://reqres.in/img/')}.size()", is(perPageEsperado)
                );
    }

    private int retornaPerPageEsperado(int page) {
        int perPageEsperado = given().
                                param("page",page).
                            when().
                                get(LISTA_USUARIOS_ENPOINT).
                            then().
                                statusCode(HttpStatus.SC_OK).  //para garantir q a API esta acessivel
                                extract().
                                path("per_page");
        return perPageEsperado;
    }


}

//    O projeto deve conter ao menos um cenário para cada um dos seguintes métodos do endpoint USERS:
//        2.1 - Validar o método POST
//        2.2 - Validar o método GET SINGLE USER
//
//        CENÁRIOS BÔNUS:
//        2.3 - Valide o método GET LIST USERS
//        2.4 - Valide o método PATCH
