package org.silverstar.category.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryStateTest {

    @Test
    void 성공() {
        //given
        CategoryState state = CategoryState.create(Boolean.TRUE, Boolean.FALSE);

        //then
        assertNotNull(state);
    }

    @Test
    void 실패_useYn이_null() {
        assertThrows(IllegalArgumentException.class, () -> CategoryState.create(null, Boolean.FALSE));
    }

    @Test
    void 실패_dispYn이_null() {
        assertThrows(IllegalArgumentException.class, () -> CategoryState.create(Boolean.TRUE, null));
    }

}