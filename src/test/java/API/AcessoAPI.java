package API;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;

public class AcessoAPI {
        @BeforeClass
        public static void setup() {
            //habilita log em caso de falha
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            //configurar caminho comum da API
            baseURI = "https://reqres.in";
            basePath = "/api";
        }
    }


