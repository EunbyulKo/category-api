package org.silverstar.category.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest extends CategoryTemplate {

    @Test
    void equals() {
        //given
        Category category1 = Category.create(100L, name, parentId, state);
        Category category2 = Category.create(100L, name, parentId, state);

        //then
        assertEquals(category1, category2);
    }

    @Test
    void isTopLevel_true() {
        //given
        Category category = Category.create(100L, name, null, state);

        //then
        assertTrue(category.isTopLevel());
    }

    @Test
    void isTopLevel_false() {
        //given
        Category category = Category.create(100L, name, parentId, state);

        //then
        assertFalse(category.isTopLevel());
    }

}