package org.silverstar.category.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.silverstar.category.controller.common.Response;
import org.silverstar.category.controller.converter.CategoryConverter;
import org.silverstar.category.controller.dto.CategoryResponseDto;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.silverstar.category.controller.dto.UpdateCategoryRequestDto;
import org.silverstar.category.domain.Category;
import org.silverstar.category.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public Response<CategoryResponseDto> getCategory(@PathVariable(name = "categoryId") @NotNull Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        CategoryResponseDto responseDto = CategoryConverter.toResponseDto(category);
        return Response.ok(responseDto);
    }

    @GetMapping("/children/{categoryId}")
    public Response<List<CategoryResponseDto>> getChildrenCategory(@PathVariable(name = "categoryId") @NotNull Long parentId) {
        List<Category> categories = categoryService.getCategoryByParentId(parentId);
        List<CategoryResponseDto> responseDtos = CategoryConverter.toResponseDtos(categories);
        return Response.ok(responseDtos);
    }

    @GetMapping("/preview/children/{categoryId}")
    public Response<List<Long>> getPreviewChildrenIds(@PathVariable(name = "categoryId") @NotNull Long parentId) {
        List<Long> ids = categoryService.getChildIds(parentId);
        return Response.ok(ids);
    }

    @PostMapping
    public Response<Long> createPost(@RequestBody @Valid CreateCategoryRequestDto dto) {
        Category category = categoryService.createCategory(dto);
        return Response.ok(category.getId());
    }

    @PatchMapping
    public Response<Long> updatePost(@RequestBody @Valid UpdateCategoryRequestDto dto) {
        Category category = categoryService.updateCategory(dto);
        return Response.ok(category.getId());
    }

}
