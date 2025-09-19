package org.silverstar.category.repository;

import lombok.RequiredArgsConstructor;
import org.silverstar.category.domain.Category;
import org.silverstar.category.repository.dsl.CategoryQueryRepository;
import org.silverstar.category.repository.entity.CategoryEntity;
import org.silverstar.category.repository.jpa.JpaCategoryRepository;
import org.silverstar.category.service.dto.CategoryPreviewDto;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        CategoryEntity categoryEntity = jpaCategoryRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return Optional.ofNullable(categoryEntity.toCategory());
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        List<CategoryEntity> categoryEntities = jpaCategoryRepository.findAllByParentId(parentId);
        return categoryEntities.stream()
                .map(CategoryEntity::toCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findChildIds(Long parentId) {
        return jpaCategoryRepository.findChildIds(parentId);
    }

    @Override
    public Category create(Category category) {
        CategoryEntity entity = CategoryEntity.createCategory(category);
        entity = jpaCategoryRepository.save(entity);
        return entity.toCategory();
    }

    public Category update(Category category) {
        CategoryEntity entity = jpaCategoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + category.getId()));
        entity.updateCategory(category);
        entity = jpaCategoryRepository.save(entity);
        return entity.toCategory();
    }

    @Override
    public List<CategoryPreviewDto> getPreviewChildren(Long id) {
        return categoryQueryRepository.getPreviewChildren(id);
    }
}
