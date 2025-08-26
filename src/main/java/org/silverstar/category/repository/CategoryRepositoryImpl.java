package org.silverstar.category.repository;

import lombok.RequiredArgsConstructor;
import org.silverstar.category.domain.Category;
import org.silverstar.category.repository.entity.CategoryEntity;
import org.silverstar.category.repository.jpa.JpaCategoryRepository;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

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
    public Category save(Category category) {
        CategoryEntity entity = new CategoryEntity(category);
        entity = jpaCategoryRepository.save(entity);
        return entity.toCategory();
    }
}
