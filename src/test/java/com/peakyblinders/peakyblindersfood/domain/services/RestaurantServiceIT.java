package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import com.peakyblinders.peakyblindersfood.domain.repositories.KitchenRepository;
import com.peakyblinders.peakyblindersfood.domain.repositories.RestaurantRepository;
import com.peakyblinders.peakyblindersfood.util.DatabaseCleaner;
import com.peakyblinders.peakyblindersfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RestaurantServiceIT {


    private static final String  ERROR_BUSINESS = "Violação de regra de negócio";

    private static final String INVALID_DATA = "Dados inválidos";


    private static final int RESTAURANT_NON_EXISTENT = 100;
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    KitchenRepository kitchenRepository;

    private Restaurant burguerTopRestaurant;

    private  String jsonCorrectRestaurantOliveGardenCorrect;

    private String jsonRestaurantOliveGardenNotKitchen;

    private String jsonRestaurantOliveGardenNotShippingFee;

    private String jsonRestaurantOliveGardenKitchenNonExistent;
    @BeforeEach
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath ="/restaurants";

        jsonCorrectRestaurantOliveGardenCorrect = ResourceUtils.getContentFromResource(
                "/json/correct/restaurant-olive-garden.json");

        jsonRestaurantOliveGardenNotShippingFee = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-olive-garden-not-shipping-fee.json");

        jsonRestaurantOliveGardenNotKitchen = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-olive-garden-not-kitchen.json");

        jsonRestaurantOliveGardenKitchenNonExistent = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-olive-garden-kitchen-non-existent.json");

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void shouldReturnStatus200_WhenQueryingRestaurants(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void mustReturnStatus201_WhenRegisteringRestaurant(){
       RestAssured.given()
                .body(jsonCorrectRestaurantOliveGardenCorrect)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
   }

   @Test
   public void mustReturnStatus400_WhenRegisterRestaurantWithoutTaxShipping(){
        RestAssured.given()
               .body(jsonRestaurantOliveGardenNotShippingFee)
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .when()
               .post()
               .then()
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .body("title", CoreMatchers.equalTo(INVALID_DATA));
   }


    @Test
    public void mustReturnStatus400_WhenRegisterRestaurantWithoutKitchen(){
        System.out.println("AKI   ---->>>  " + jsonRestaurantOliveGardenNotKitchen);
        RestAssured.given()
                .body(jsonRestaurantOliveGardenNotKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", CoreMatchers.equalTo(INVALID_DATA));
    }


    @Test
    public void mustReturnStatus400_WhenRegisterRestaurantKitchenNonExistent(){
        RestAssured.given()
                .body(jsonRestaurantOliveGardenKitchenNonExistent)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", CoreMatchers.equalTo(ERROR_BUSINESS));
    }

    @Test
    public void mustReturnResponseStatus200_Correct_WhenConsultExistingRestaurant(){
        RestAssured.given()
                .pathParams("restaurantId", burguerTopRestaurant.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.OK.value());
    } @Test
    public void mustReturnResponseStatus404_Incorrect_WhenConsultNonExistentRestaurant(){
        RestAssured.given()
                .pathParams("restaurantId", RESTAURANT_NON_EXISTENT)
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
    private void prepareData(){
        Kitchen kitchenItaly = new Kitchen();
        kitchenItaly.setName("Italiana");
        kitchenRepository.save(kitchenItaly);

        Kitchen kitchenAmerican = new Kitchen();
        kitchenAmerican.setName("Americana");
        kitchenRepository.save(kitchenAmerican);

        burguerTopRestaurant = new Restaurant();
        burguerTopRestaurant.setName("Burger Top");
        burguerTopRestaurant.setShippingFee(new BigDecimal(10));
        burguerTopRestaurant.setKitchen(kitchenAmerican);
        restaurantRepository.save(burguerTopRestaurant);



    }
}