package org.silverstar.category.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryUpdateTest extends CategoryTemplate {

    Long id = 200L;
    Category category = Category.create(id, name, parentId, state, images);

    @Test
    void 성공() {
        //when
        category.updateName(name);
        category.updateState(state);
        category.updateParentId(parentId);
        category.updateImages(images);

        //then
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(state, category.getState());
        assertEquals(parentId, category.getParentId());
        assertEquals(images, category.getImages());
    }

    @Test
    void 실패_name이_null() {
        // when
        String name = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> category.updateName(name));
    }

    @Test
    void 실패_name이_empty() {
        // when
        String name = "";

        // then
        assertThrows(IllegalArgumentException.class, () -> category.updateName(name));
    }

    @Test
    void 실패_name이_50자이상() {
        // when
        String name = "50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자50자";

        // then
        assertThrows(IllegalArgumentException.class, () -> category.updateName(name));
    }

    @Test
    void 실패_state가_null() {
        // when
        CategoryState state = null;

        // then
        assertThrows(IllegalArgumentException.class, () -> category.updateState(state));
    }

    @Test
    void 실패_images가_null() {
        // when
        List<CategoryImage> images = null;

        // then
        assertThrows(NullPointerException.class, () -> category.updateImages(images));
    }

    @Test
    void 실패_images가_size1() {
        // when
        List<CategoryImage> images = List.of(CategoryImage.create("url"));

        // then
        assertThrows(IllegalArgumentException.class, () -> category.updateImages(images));
    }

}