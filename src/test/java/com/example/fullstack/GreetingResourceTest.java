package com.example.fullstack;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// Quarkus test desteği sağlayan sınıf
@QuarkusTest
class GreetingResourceTest {

    // /hello endpoint'inin doğru yanıt verip vermediğini test eder
    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello") // /hello endpoint'ine GET isteği gönder
                .then()
                .statusCode(200) // 200 OK yanıt kodunu bekler
                .body(is("Hello from RESTEasy Reactive")); // Yanıtın beklenen "Hello from RESTEasy Reactive" metniyle eşleşmesini bekler
    }

    // /hello/world endpoint'inin doğru yanıt verip vermediğini test eder
    @Test
    public void testHelloWorldEndpoint() {
        given()
                .when().get("/hello/world") // /hello/world endpoint'ine GET isteği gönder
                .then()
                .statusCode(200) // 200 OK yanıt kodunu bekler
                .body(is("Hello world!!")); // Yanıtın beklenen "Hello world!!" metniyle eşleşmesini bekler
    }
}
