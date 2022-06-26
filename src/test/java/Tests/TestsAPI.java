package Tests;

import API.AcessoAPI;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class TestsAPI extends  AcessoAPI{

    @Test
    public void testeListaDadosUsuario() {
        when().
                get("users?page=2").
                then().
                statusCode(200).
                body("page", is(2)).
                body("data", is(notNullValue()));
    }

    @Test
    public void testeCriaUsuario(){
        given().
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"name\": \"edson\",\n" +
                        "    \"job\": \"QA\"\n" + "}").
                when().
                post("users").
                then().
                statusCode(201).
                body("name", is("edson"));
    }

}
