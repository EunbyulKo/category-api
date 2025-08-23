package org.silverstar.category.controller.dto;

public record UpdateCategoryRequestDto(Long id, String name, Long parentId, Boolean dispYn, Boolean useYn) {
}
