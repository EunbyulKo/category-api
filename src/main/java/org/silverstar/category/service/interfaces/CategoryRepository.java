package org.silverstar.category.service.interfaces;

import org.silverstar.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);
    List<Category> findByParentId(Long parentId);
    Category save(Category post);

}
