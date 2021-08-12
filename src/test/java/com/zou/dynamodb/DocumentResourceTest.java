package com.zou.dynamodb;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DocumentResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/C:/Program Files/Git/documents")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}