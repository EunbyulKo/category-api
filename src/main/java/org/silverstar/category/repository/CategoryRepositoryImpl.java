package org.silverstar.category.repository;

import org.silverstar.category.domain.Category;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return List.of();
    }

    @Override
    public Category save(Category post) {
        return null;
    }
}
