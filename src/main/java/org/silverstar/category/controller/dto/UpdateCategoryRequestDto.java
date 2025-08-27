package org.silverstar.category.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequestDto(
        @NotNull Long id,
        @NotBlank String name,
        Long parentId,
        @NotNull Boolean dispYn,
        @NotNull Boolean useYn) {
}
