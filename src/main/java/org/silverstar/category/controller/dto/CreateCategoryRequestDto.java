package org.silverstar.category.controller.dto;

public record CreateCategoryRequestDto(String name, Long parentId, Boolean dispYn, Boolean useYn) {
}
