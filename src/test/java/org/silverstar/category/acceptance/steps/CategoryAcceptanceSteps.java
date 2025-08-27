package org.silverstar.category.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.springframework.http.MediaType;

public class CategoryAcceptanceSteps {

    public static ExtractableResponse<Response> getCategory(Long categoryId) {
        return RestAssured
                .given()
                .pathParam("categoryId", categoryId)
                .when()
                .get("/category/{categoryId}")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> createCategory(CreateCategoryRequestDto dto) {
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/category")
                .then()
                .extract();
    }
}
