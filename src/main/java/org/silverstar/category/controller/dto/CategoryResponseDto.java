package org.silverstar.category.controller.dto;

public record CategoryResponseDto(Long id, String name, Long parentId, String useYn, String dispYn) {}
