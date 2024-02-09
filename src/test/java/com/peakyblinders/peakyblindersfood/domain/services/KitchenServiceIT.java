package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import com.peakyblinders.peakyblindersfood.domain.repositories.KitchenRepository;
import com.peakyblinders.peakyblindersfood.util.DatabaseCleaner;
import com.peakyblinders.peakyblindersfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;




@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class KitchenServiceIT {


    private static final int NON_EXISTENT_KITCHEN = 100;


    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    private Kitchen kitchenAmerican;

    private int quantityRegisteredKitchens;

    private String jsonCorrectKitchenChinese;


    @BeforeEach
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath ="/kitchens";

        jsonCorrectKitchenChinese = ResourceUtils.getContentFromResource(
                "/json/correct/kitchen-chinese.json");
        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturnStatus200_WhenQueryingKitchens(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void  musContainAmountKitchens(){
        RestAssured.given()
                    .accept(ContentType.JSON)
               .when()
                     .get()
                .then()
                .body("",Matchers.hasSize(quantityRegisteredKitchens));

    }
    @Test
    public void mustReturnStatus201_WhenRegisteringKitchen(){
        RestAssured.given()
                .body(jsonCorrectKitchenChinese)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnCorrectResponseAndStatus_WhenQueryingExistingKitchen(){
        RestAssured.given()
                .pathParams("kitchenId", kitchenAmerican.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", CoreMatchers.equalTo(kitchenAmerican.getName()));
    }
    @Test
    public void shouldReturnCorrectResponseAndStatus404_WhenQueryingNonExistentKitchen(){
        RestAssured.given()
                .pathParams("kitchenId", NON_EXISTENT_KITCHEN)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData(){
        Kitchen kitchenThai = new Kitchen();
        kitchenThai.setName("Tailandesa");
        kitchenRepository.save(kitchenThai);

        kitchenAmerican = new Kitchen();
        kitchenAmerican.setName("Americana");
        kitchenRepository.save(kitchenAmerican);
        quantityRegisteredKitchens = (int) kitchenRepository.count();
    }
}