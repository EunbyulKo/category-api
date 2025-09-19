package org.silverstar.category.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryImageTest {

    @Test
    void 성공() {
        //given
        CategoryImage image = CategoryImage.create("url");

        //then
        assertNotNull(image);
    }

    @Test
    void 실패() {
        assertThrows(IllegalArgumentException.class, () -> CategoryImage.create(""));
    }

}