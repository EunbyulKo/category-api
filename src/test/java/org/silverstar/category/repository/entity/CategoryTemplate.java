package org.silverstar.category.repository.entity;

import org.silverstar.category.domain.CategoryImage;
import org.silverstar.category.domain.CategoryState;

import java.util.Arrays;
import java.util.List;

class CategoryTemplate {

    Long parentId = 100L;
    String name = "카테고리이름";
    CategoryState state = CategoryState.create(Boolean.TRUE, Boolean.FALSE);

    CategoryImage image = CategoryImage.create("url1");
    List<CategoryImage> images = Arrays.asList(image, image);
    
}