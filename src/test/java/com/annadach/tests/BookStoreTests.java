package com.annadach.tests;

import com.annadach.lombok.LombokBooksData;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static com.annadach.specs.Specs.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import com.annadach.config.Config;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.hasItem;


public class BookStoreTests {
    public static Config config = ConfigFactory.create(Config.class);

    @Test
    void getBooksList() {

        LombokBooksData bookData =  given()
                .spec(booksRequest)
                .when()
                .get(basePath)
                .then()
                .spec(booksResponse)
                .log().body()
                .extract().as(LombokBooksData.class);

        assertEquals("9781449325862", bookData.getBook().get(0).getIsbn());
        assertEquals("Git Pocket Guide", bookData.getBook().get(0).getTitle());
        assertEquals("A Working Introduction", bookData.getBook().get(0).getSubTitle());
        assertEquals("Richard E. Silverman", bookData.getBook().get(0).getAuthor());
        assertEquals("2020-06-04T08:48:39.000Z", bookData.getBook().get(0).getPublishDate());
        assertEquals("O'Reilly Media", bookData.getBook().get(0).getPublisher());
        assertEquals(234, bookData.getBook().get(0).getPages());
    }

    @Test
    void getBooksListWithGroovy() {

        given()
                .spec(booksRequest)
                .when()
                .get(basePath)
                .then()
                .spec(booksResponse)
                .log().body()
                .body("books.findAll{it.author}.author.flatten()",
                        hasItem("Axel Rauschmayer"));

    }


    @Test
    void authorizeWithLogin() {
        String data = "{\"userName\": \""+config.userName()+"\", \"password\": \""+config.password()+"\"}";

        given()
                .spec(authorizeRequest)
                .body(data)
                .when()
                .post(basePath)
                .then()
                .spec(authorizeResponse);
    }
}
