package org.silverstar.category.service;

import org.silverstar.category.service.interfaces.CategoryRepository;
import org.silverstar.category.domain.Category;
import org.silverstar.category.domain.CategoryState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeCategoryRepository implements CategoryRepository {

    private List<Category> categories = new ArrayList<>();

    public FakeCategoryRepository() {
        String name = "카테고리이름";
        CategoryState state = CategoryState.create(Boolean.TRUE, Boolean.FALSE);

        categories.add(Category.create(100L, name, null, state));

        categories.add(Category.create(200L, name, 100L, state));
        categories.add(Category.create(201L, name, 100L, state));

        categories.add(Category.create(300L, name, 200L, state));
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        if (parentId == null) {
            return categories.stream()
                    .filter(category -> category.getParentId() == null)
                    .toList();
        }

        return categories.stream()
                .filter(category -> category.getParentId() != null
                        && category.getParentId().equals(parentId))
                .toList();
    }

    @Override
    public List<Long> findChildIds(Long parentId) {
        return findByParentId(parentId).stream().map(Category::getId).toList();
    }

    @Override
    public Category save(Category post) {
        return post;
    }
}
