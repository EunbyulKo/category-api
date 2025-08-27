package org.silverstar.category.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.silverstar.category.acceptance.steps.CategoryAcceptanceSteps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoryReadAcceptanceTest extends AcceptanceTestTemplate {

    @BeforeEach
    void init() {
        super.setUp();
    }

    @Test
    void readSingleCategory_success() {
        Long categoryId = 1L;

        // when
        var res = CategoryAcceptanceSteps.getCategory(categoryId);

        // then
        assertThat(res.statusCode()).isEqualTo(200);
        assertThat(res.jsonPath().getInt("code")).isEqualTo(0);
        assertThat(res.jsonPath().getLong("value.id")).isEqualTo(categoryId);
        assertThat(res.jsonPath().getString("value.name")).isNotBlank();
    }

    @Test
    void readSingleCategory_notFound() {
        var res = CategoryAcceptanceSteps.getCategory(9999L);

        assertThat(res.statusCode()).isEqualTo(500);
        assertThat(res.jsonPath().getInt("code")).isEqualTo(500);
    }

}
