package br.fabioleal.apt.test;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://192.168.1.25:8001/task-backend/";
    }

    @Test
    public static void deveRetornarTaferas() {
        RestAssured.given()
        .when()
            .get("/todo/1")
        .then()
            .statusCode(200)
        ;
    }
    
    @Test
    public static void deveRetornarSucesso() {
        RestAssured.given()
            .body("{\"taks\": \"Test via api\", \"dueDate\": \"2023-12-30\" }")
            .contentType(ContentType.JSON)
        .when()
            .post("/todo")
        .then()
        	.log().all()
            .statusCode(201)
        ;
    }
	
    
    @Test
    public static void naodeveAdcionarTarefaInvalida() {
        RestAssured.given()
            .body("{\"taks\": \"Test via api\", \"dueDate\": \"2023-12-30\" }")
            .contentType(ContentType.JSON)
        .when()
            .post("/todo")
        .then()
        	.log().all()
            .statusCode(400)
            .body("message", CoreMatchers.is("Due date must not be is past"))
        ;
    }
}


