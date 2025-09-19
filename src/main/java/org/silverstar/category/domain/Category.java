package org.silverstar.category.domain;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Category {

    private final Long id;
    private String name;
    private Long parentId;
    private CategoryState state;
    private List<CategoryImage> images;

    public static Category create(Long id, String name, Long parentId, CategoryState state, List<CategoryImage> images) {
        return new Category(id, name, parentId, state, images);
    }

    private Category(Long id, String name, Long parentId, CategoryState state, List<CategoryImage> images) {
        validateName(name);
        validateState(state);
        validateImages(images);

        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.state = state;
        this.images = images;
    }

    public void updateName(String name) {
        validateName(name);
        this.name = name;
    }

    public void updateParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void updateState(CategoryState state) {
        validateState(state);
        this.state = state;
    }

    public void updateImages(List<CategoryImage> images) {
        validateImages(images);
        this.images = images;
    }

    public boolean isTopLevel() {
        return parentId == null;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }

        if (name.length() > 50) {
            throw new IllegalArgumentException("name length must be 50 characters or less");
        }
    }

    private void validateState(CategoryState state) {
        if (state == null) {
            throw new IllegalArgumentException("state cannot be null");
        }
    }

    private void validateImages(List<CategoryImage> images) {
        if (images.size() != 2) {
            throw new IllegalArgumentException("Exactly 2 images are required");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
