package org.silverstar.category.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateCategoryRequestDto(
        @NotBlank String name,
        Long parentId,
        @NotNull Boolean dispYn,
        @NotNull Boolean useYn,
        @Size(min = 2, max = 2, message = "Exactly 2 image URLs are required")
        List<String> imageUrl) {

}
