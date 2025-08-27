package org.silverstar.category.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCategoryRequestDto(
        @NotBlank String name,
        Long parentId,
        @NotNull Boolean dispYn,
        @NotNull Boolean useYn) {
}
