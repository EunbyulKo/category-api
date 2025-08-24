package org.silverstar.category.repository;

import org.silverstar.category.domain.CategoryState;

class CategoryTemplate {

    Long parentId = 100L;
    String name = "카테고리이름";
    CategoryState state = CategoryState.create(Boolean.TRUE, Boolean.FALSE);

}