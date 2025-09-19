package org.silverstar.category.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest extends CategoryTemplate {

    //TODO : Category.create 빌더 패턴으로 수정 (필드가 추가될 때마다 수정해야할 곳이 너무 많아짐)
    @Test
    void equals() {
        //given
        Category category1 = Category.create(100L, name, parentId, state, images);
        Category category2 = Category.create(100L, name, parentId, state, images);

        //then
        assertEquals(category1, category2);
    }

    @Test
    void isTopLevel_true() {
        //given
        Category category = Category.create(100L, name, null, state, images);

        //then
        assertTrue(category.isTopLevel());
    }

    @Test
    void isTopLevel_false() {
        //given
        Category category = Category.create(100L, name, parentId, state, images);

        //then
        assertFalse(category.isTopLevel());
    }

}