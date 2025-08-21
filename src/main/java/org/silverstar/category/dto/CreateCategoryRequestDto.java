package org.silverstar.category.dto;

public record CreateCategoryRequestDto(String name, Long parentId, Boolean dispYn, Boolean useYn) {
}
