package org.silverstar.category.domain;

import java.util.Objects;

public class Category {

    private final Long id;
    private String name;
    private Long parentId;
    private CategoryState state;

    public static Category create(Long id, String name, Long parentId, CategoryState state) {
        return new Category(id, name, parentId, state);
    }

    private Category(Long id, String name, Long parentId, CategoryState state) {
        validateName(name);
        validateState(state);

        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.state = state;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public CategoryState getState() {
        return state;
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
