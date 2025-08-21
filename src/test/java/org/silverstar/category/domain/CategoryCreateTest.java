package org.silverstar.category.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCreateTest extends CategoryTemplate {

    @Test
    void 성공() {
        //given
        Category category = Category.create(null, name, parentId, state);

        //then
        assertNotNull(category);
        assertEquals(name, category.getName());
        assertEquals(state, category.getState());
        assertEquals(parentId, category.getParentId());
    }

    @Test
    void 실패_name이_null() {
        // when
        String name = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> Category.create(null, name, parentId, state));
    }

    @Test
    void 실패_name이_empty() {
        // when
        String name = "";

        // then
        assertThrows(IllegalArgumentException.class, () -> Category.create(null, name, parentId, state));
    }

    @Test
    void 실패_name이_50자이상() {
        // when
        String name = "50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자";

        // then
        assertThrows(IllegalArgumentException.class, () -> Category.create(null, name, parentId, state));
    }

    @Test
    void 실패_state가_null() {
        // when
        CategoryState state = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> Category.create(null, name, parentId, state));
    }

}