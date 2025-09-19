package org.silverstar.category.service;

import org.silverstar.category.domain.CategoryImage;
import org.silverstar.category.service.dto.CategoryPreviewDto;
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
        CategoryImage image = CategoryImage.create("url");
        List<CategoryImage> images = List.of(image, image);

        categories.add(Category.create(100L, name, null, state, images));

        categories.add(Category.create(200L, name, 100L, state, images));
        categories.add(Category.create(201L, name, 100L, state, images));

        categories.add(Category.create(300L, name, 200L, state, images));
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
    public Category create(Category post) {
        return post;
    }

    @Override
    public Category update(Category post) {
        return post;
    }

    @Override
    public List<CategoryPreviewDto> getPreviewChildren(Long id) {
        return List.of();
    }
}
