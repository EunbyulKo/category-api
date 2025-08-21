package org.silverstar.category.application;

import org.junit.jupiter.api.Test;
import org.silverstar.category.application.interfaces.CategoryRepository;
import org.silverstar.category.domain.Category;
import org.silverstar.category.dto.CreateCategoryRequestDto;
import org.silverstar.category.dto.UpdateCategoryRequestDto;

import static org.junit.jupiter.api.Assertions.*;

class CategorySaveServiceTest {

    private CategoryRepository fakeCategoryRepository = new FakeCategoryRepository();
    private CategoryService categoryService = new CategoryService(fakeCategoryRepository);

    Long parentId = 100L;
    String name = "카테고리이름";

    @Test
    void create_성공() {
        //given
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(name, parentId, Boolean.TRUE, Boolean.FALSE);

        //when
        Category category = categoryService.createCategory(dto);

        //then
        assertNotNull(category);
        assertNull(category.getId());
    }

    @Test
    void update_성공() {
        //given
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(100L, name, parentId, Boolean.TRUE, Boolean.FALSE);

        //when
        Category category = categoryService.updateCategory(dto);

        //then
        assertNotNull(category);
        assertNotNull(category.getId());
    }

    @Test
    void update_실패_존재하지않는_ID() {
        //given
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(500L, name, parentId, Boolean.TRUE, Boolean.FALSE);

        //then
        assertThrows(IllegalArgumentException.class, () -> categoryService.updateCategory(dto));
    }

}