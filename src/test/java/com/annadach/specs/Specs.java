package com.annadach.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.annadach.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.containsString;

public class Specs {

    public static RequestSpecification booksRequest = with ()
            .filter(customLogFilter().withCustomTemplates())
            .baseUri("https://demoqa.com/BookStore")
            .basePath("/v1/Books")
            .log().all();

    public static ResponseSpecification booksResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static RequestSpecification authorizeRequest = with ()
            .filter(customLogFilter().withCustomTemplates())
            .contentType("application/json")
            .accept("application/json")
            .baseUri("https://demoqa.com/Account")
            .basePath("/v1/GenerateToken")
            .log().uri()
            .log().body();

    public static ResponseSpecification authorizeResponse  = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .expectBody(containsString("success"))
            .expectBody(containsString("User authorized successfully."))
            .build();

}
