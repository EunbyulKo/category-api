package org.silverstar.category.application;

import org.junit.jupiter.api.Test;
import org.silverstar.category.application.interfaces.CategoryRepository;
import org.silverstar.category.domain.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryReadServiceTest {

    private CategoryRepository fakeCategoryRepository = new FakeCategoryRepository();
    private CategoryService categoryService = new CategoryService(fakeCategoryRepository);


    @Test
    void getCategory_존재() {
        //when
        Category category = categoryService.getCategory(100L);

        //then
        assertNotNull(category);
    }

    @Test
    void getCategory_존재하지않음() {
        //when
        Long id = 500L;

        //then
        assertThrows(IllegalArgumentException.class, () -> categoryService.getCategory(id));
    }

    @Test
    void getCategoryByParentId_depth0() {
        //when
        List<Category> categories = categoryService.getCategoryByParentId(null);

        //then
        assertEquals(1, categories.size());
    }

    @Test
    void getCategoryByParentId_depth1() {
        //when
        List<Category> categories = categoryService.getCategoryByParentId(100L);

        //then
        assertEquals(2, categories.size());
    }

}