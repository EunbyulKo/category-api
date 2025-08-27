package org.silverstar.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.silverstar.category.controller.dto.UpdateCategoryRequestDto;
import org.silverstar.category.domain.Category;
import org.silverstar.category.domain.CategoryState;
import org.silverstar.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryUpdateControllerTest {

    @Autowired MockMvc mvc;
    @MockBean CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        Category mockCategory = Category.create(1L, "카테고리A", null, CategoryState.create(true, true));
        Mockito.when(categoryService.updateCategory(any(UpdateCategoryRequestDto.class)))
                .thenReturn(mockCategory);
    }

    @Test
    void updateCategory_success() throws Exception {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(
                1L, "카테고리수정", 2L, true, true
        );

        mvc.perform(patch("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").isNumber());
    }

    @Test
    void updateCategory_fail_missingName() throws Exception {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(
                1L, "", 2L, true, true   // name이 빈 문자열 → @NotBlank 위반
        );

        mvc.perform(
                        patch("/category")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void updateCategory_fail_missingId() throws Exception {
        UpdateCategoryRequestDto dto = new UpdateCategoryRequestDto(
                null, "카테고리수정", 2L, true, true
        );

        mvc.perform(
                        patch("/category")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isInternalServerError());
    }


}