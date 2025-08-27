package org.silverstar.category.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryReadControllerTest {

    @Autowired MockMvc mvc;
    @MockBean CategoryService categoryService;

    @BeforeEach
    void setup() {
        Category mockCategory = Category.create(100L, "카테고리A", null, CategoryState.create(true, true));
        Mockito.when(categoryService.updateCategory(any(UpdateCategoryRequestDto.class)))
                .thenReturn(mockCategory);
    }

    @Test
    void getCategory_200() throws Exception {
        mvc.perform(get("/category/{id}", 100L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("ok"));
    }

    @Test
    void getCategory_string_500() throws Exception {
        mvc.perform(get("/category/{id}", "test"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getCategory_not_exist_200() throws Exception {
        mvc.perform(get("/category/{id}", 5000L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.value").doesNotExist());
    }


}