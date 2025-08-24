package org.silverstar.category.controller;

import lombok.RequiredArgsConstructor;
import org.silverstar.category.controller.common.Response;
import org.silverstar.category.controller.converter.CategoryConverter;
import org.silverstar.category.controller.dto.CategoryResponseDto;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.silverstar.category.controller.dto.UpdateCategoryRequestDto;
import org.silverstar.category.domain.Category;
import org.silverstar.category.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public Response<CategoryResponseDto> getCategory(@PathVariable(name = "categoryId") Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        CategoryResponseDto responseDto = CategoryConverter.toResponseDto(category);
        return Response.ok(responseDto);
    }

    @GetMapping("/children/{categoryId}")
    public Response<List<CategoryResponseDto>> getChildrenCategory(@PathVariable(name = "categoryId") Long parentId) {
        List<Category> categories = categoryService.getCategoryByParentId(parentId);
        List<CategoryResponseDto> responseDtos = CategoryConverter.toResponseDtos(categories);
        return Response.ok(responseDtos);
    }

    @PostMapping
    public Response<Long> createPost(@RequestBody CreateCategoryRequestDto dto) {
        Category category = categoryService.createCategory(dto);
        return Response.ok(category.getId());
    }

    @PatchMapping
    public Response<Long> updatePost(@RequestBody UpdateCategoryRequestDto dto) {
        Category category = categoryService.updateCategory(dto);
        return Response.ok(category.getId());
    }

}
