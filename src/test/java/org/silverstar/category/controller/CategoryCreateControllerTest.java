package org.silverstar.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.silverstar.category.domain.Category;
import org.silverstar.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryCreateControllerTest {

    @Autowired MockMvc mvc;
    @MockBean CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper; // JSON 직렬화용

    @Test
    void createCategory_success() throws Exception {
        Category returned = mock(Category.class);
        when(returned.getId())
                .thenReturn(1L);
        when(categoryService.createCategory(any(CreateCategoryRequestDto.class)))
                .thenReturn(returned);

        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(
                "카테고리A", 1L, true, true
        );

        mvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.value").isNumber());

        verify(categoryService, times(1))
                .createCategory(any(CreateCategoryRequestDto.class));

    }

    @Test
    void createCategory_fail_missingName() throws Exception {
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(
                "", 1L, true, true
        );

        mvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500));
    }


}