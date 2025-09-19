package org.silverstar.category.controller.converter;

import org.silverstar.category.controller.dto.CategoryResponseDto;
import org.silverstar.category.domain.Category;
import org.silverstar.category.common.contant.YNType;
import org.silverstar.category.domain.CategoryImage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

    public static List<CategoryResponseDto> toResponseDtos(List<Category> categories) {
        if (categories == null || categories.isEmpty()) return Collections.emptyList();

        return categories.stream()
                .map(CategoryConverter::toResponseDto)
                .collect(Collectors.toList());
    }

    public static CategoryResponseDto toResponseDto(Category category) {
        if (category == null) return null;

        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getParentId(),
                YNType.fromBoolean(category.getState().isUseYn()),
                YNType.fromBoolean(category.getState().isDispYn()),
                category.getImages().stream().map(CategoryImage::getImageUrl).toList()
        );
    }
}
