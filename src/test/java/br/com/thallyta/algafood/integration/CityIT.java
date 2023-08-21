package br.com.thallyta.algafood.integration;

import br.com.thallyta.algafood.common.utils.DatabaseCleaner;
import br.com.thallyta.algafood.common.utils.ResourceUtils;
import br.com.thallyta.algafood.models.City;
import br.com.thallyta.algafood.models.Kitchen;
import br.com.thallyta.algafood.models.State;
import br.com.thallyta.algafood.repositories.CityRepository;
import br.com.thallyta.algafood.repositories.KitchenRepository;
import br.com.thallyta.algafood.repositories.StateRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CityIT {

    private static final Long CITY_ID_DOES_NOT_EXIST = 100L;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    private City americanaCity;
    private int numberRegisteredCities;
    private String jsonCity;

    @BeforeEach
    public void setUp() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cities";
        databaseCleaner.clearTables();

        insertNewDataInCityTable();

        jsonCity = ResourceUtils.getContentFromResource(
                "/json/correct/city.json");

    }

    @Test
    void whenGetAllCitiesThenReturnHttp200() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
                given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturnHttp404StatusWhenGetKitchenDoesNotExist() {
        given()
                .pathParam("id", CITY_ID_DOES_NOT_EXIST)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturnCorrectResponseAndStatusWhenGetKitchenByExistingId() {
        given()
                .pathParam("id", americanaCity.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(americanaCity.getName()));
    }

    @Test
    void shouldReturnAmountCorrectOfKitchensWhenGetAllKitchens() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(numberRegisteredCities));
    }

    @Test
    void shouldReturnHttp201StatusWhenSaveKitchen() {
        given()
                .body(jsonCity)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    void insertNewDataInCityTable() {
        State americanaState = new State();
        americanaState.setId(1L);
        americanaState.setName("Rio de Janeiro");
        stateRepository.save(americanaState);

        americanaCity = new City();
        americanaCity.setName("Americana");
        americanaCity.setState(americanaState);
        cityRepository.save(americanaCity);

        numberRegisteredCities = (int) cityRepository.count();
    }
}
