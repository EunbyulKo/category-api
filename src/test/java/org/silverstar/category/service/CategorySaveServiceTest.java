package org.silverstar.category.service;

import org.junit.jupiter.api.Test;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.silverstar.category.domain.Category;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.silverstar.category.controller.dto.UpdateCategoryRequestDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategorySaveServiceTest {

    private CategoryRepository fakeCategoryRepository = new FakeCategoryRepository();
    private CategoryService categoryService = new CategoryService(fakeCategoryRepository);

    Long parentId = 100L;
    String name = "카테고리이름";
    List<String> images = Arrays.asList("url1", "url2");

    @Test
    void create_성공() {
        //given
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(name, parentId, Boolean.TRUE, Boolean.FALSE, images);

        //when
        Category category = categoryService.createCategory(dto);

        //then
        assertNotNull(category);
        assertNull(category.getId());
    }

    @Test
    void update_성공() {
        //given
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(100L, name, parentId, Boolean.TRUE, Boolean.FALSE, images);

        //when
        Category category = categoryService.updateCategory(dto);

        //then
        assertNotNull(category);
        assertNotNull(category.getId());
    }

    @Test
    void update_실패_존재하지않는_ID() {
        //given
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(500L, name, parentId, Boolean.TRUE, Boolean.FALSE, images);

        //then
        assertThrows(IllegalArgumentException.class, () -> categoryService.updateCategory(dto));
    }

}