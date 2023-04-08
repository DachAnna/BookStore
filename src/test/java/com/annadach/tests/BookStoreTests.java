package com.annadach.tests;

import org.junit.jupiter.api.Test;

import static com.annadach.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BookStoreTests {

    @Test
    void noLogsTest() {
        given()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void withAllLogs() {
        given()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void withSomeLogs() {
        given()
                .log().uri()
                .log().body()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void authorizeWithLogin() {
        String data = "{" +
                "  \"userName\": \"annaDach\"," +
                "  \"password\": \"Qwecxz231!\"" +
                "}";

        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("application/json")
                .accept("application/json")
                .body(data)
                .when()
                .log().uri()
                .log().body()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }


    @Test
    void authorizeWithTemplatesTest() {
        String data = "{" +
                "  \"userName\": \"annaDach\"," +
                "  \"password\": \"Qwecxz231!\"" +
                "}";

        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("application/json")
                .accept("application/json")
                .body(data)
                .when()
                .log().uri()
                .log().body()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }

    @Test
    void authorizeWithShemeTest() {
        String data = "{" +
                "  \"userName\": \"annaDach\"," +
                "  \"password\": \"Qwecxz231!\"" +
                "}";

        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType("application/json")
                .accept("application/json")
                .body(data)
                .when()
                .log().uri()
                .log().body()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath("schemas/GenerateTokenSchemas.json"))
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }
}
