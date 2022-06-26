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
            //port = 8080;
            basePath = "/api/";
        }
    }

//    O projeto deve conter ao menos um cenário para cada um dos seguintes métodos do endpoint USERS:
//        2.1 - Validar o método POST
//        2.2 - Validar o método GET SINGLE USER
//
//        CENÁRIOS BÔNUS:
//        2.3 - Valide o método GET LIST USERS
//        2.4 - Valide o método PATCH
