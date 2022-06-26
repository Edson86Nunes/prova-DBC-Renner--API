package Tests;

import API.AcessoAPI;
import API.Usuario;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestsUsuario extends  AcessoAPI{

    private static final String LISTA_USUARIOS_ENPOINT ="/users";
    private static final String CRIA_USUARIO_ENPOINT ="/user";
    private static final String CONSULTA_USUARIO_ENPOINT ="/users/{userId}";

    @Test
    public void testeCriaUsuario(){                        //2.1 - Validar o método POST
        Usuario usuario = new Usuario("edson","QA");
        given().
                contentType(ContentType.JSON).
                body(usuario).
        when().
                post(CRIA_USUARIO_ENPOINT).
        then().
                statusCode(HttpStatus.SC_CREATED).
                body("name", is("edson"));
    }

    @Test                                               // 2.2 - Validar o método GET SINGLE USER
    public void testeConsultaUmUsuario(){
        Usuario usuario = given().
                pathParam("userId", "3").
        when().
                get(CONSULTA_USUARIO_ENPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                extract().
                body().jsonPath().getObject("data", Usuario.class);

        assertThat(usuario.getName(), is("Emma"));
        assertThat(usuario.getLastname(), is("Wong"));
    }

    @Test                                                   //2.3 - Valide o método GET LIST USERS
    public void testeListaDadosUsuariosPaginaEspecifica() {
        given().
                params("page", "2").
        when().
                get(LISTA_USUARIOS_ENPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                body("page", is(2),
                "data", is(notNullValue()));

    }

                                                            //2.4 - Valide o método PATCH
    @Test
    public void testeAlteraUmUsuario(){
        Usuario usuario = new Usuario("edson nunes","QA - automação");
        given().
                pathParam("userId", "4").
                contentType(ContentType.JSON).
                body(usuario).
        when().
                patch(CONSULTA_USUARIO_ENPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                body(containsString("updatedAt"));
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
                statusCode(HttpStatus.SC_OK).
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



