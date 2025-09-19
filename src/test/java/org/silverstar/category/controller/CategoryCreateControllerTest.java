package org.silverstar.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.silverstar.category.domain.Category;
import org.silverstar.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

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

    @BeforeEach
    void setup() {
        Category returned = mock(Category.class);
        when(categoryService.createCategory(any(CreateCategoryRequestDto.class)))
                .thenReturn(returned);
    }

    @Test
    void createCategory_success() throws Exception {
        List<String> images = Arrays.asList("url1", "url2");
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(
                "카테고리A", 1L, true, true, images
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
        List<String> images = Arrays.asList("url1", "url2");
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(
                "", 1L, true, true, images
        );

        mvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500));
    }

    @Test
    void createCategory_fail_imagesSize() throws Exception {
        List<String> images = Arrays.asList("url1");
        CreateCategoryRequestDto dto = new CreateCategoryRequestDto(
                "카테고리A", 1L, true, true, images
        );

        mvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(500));
    }

}