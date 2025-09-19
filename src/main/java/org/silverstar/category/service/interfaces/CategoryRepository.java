package org.silverstar.category.service.interfaces;

import org.silverstar.category.domain.Category;
import org.silverstar.category.service.dto.CategoryPreviewDto;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);
    List<Category> findByParentId(Long parentId);
    List<Long> findChildIds(Long parentId);
    Category create(Category post);
    Category update(Category post);

    List<CategoryPreviewDto> getPreviewChildren(Long id);

}
