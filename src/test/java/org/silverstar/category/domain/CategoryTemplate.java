package org.silverstar.category.domain;

import java.util.Arrays;
import java.util.List;

class CategoryTemplate {

    Long parentId = 100L;
    String name = "카테고리이름";
    CategoryState state = CategoryState.create(Boolean.TRUE, Boolean.FALSE);

    CategoryImage image = CategoryImage.create("url");
    List<CategoryImage> images = Arrays.asList(image, image);

}