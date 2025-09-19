package org.silverstar.category.controller.dto;

import java.util.List;

public record CategoryResponseDto(Long id, String name, Long parentId, String useYn, String dispYn, List<String> imageUrl) {}
