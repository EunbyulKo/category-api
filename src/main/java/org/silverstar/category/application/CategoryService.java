package org.silverstar.category.application;

import org.silverstar.category.application.interfaces.CategoryRepository;
import org.silverstar.category.domain.Category;
import org.silverstar.category.domain.CategoryState;
import org.silverstar.category.dto.CreateCategoryRequestDto;
import org.silverstar.category.dto.UpdateCategoryRequestDto;

import java.util.List;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public List<Category> getCategoryByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    public Category createCategory(CreateCategoryRequestDto dto) {
        CategoryState state = CategoryState.create(dto.useYn(), dto.dispYn());
        Category category = Category.create(null, dto.name(), dto.parentId(), state);
        return categoryRepository.save(category);
    }

    public Category updateCategory(UpdateCategoryRequestDto dto) {
        CategoryState state = CategoryState.create(dto.useYn(), dto.dispYn());

        Category category = getCategory(dto.id());

        category.updateName(dto.name());
        category.updateParentId(dto.parentId());
        category.updateState(state);

        return categoryRepository.save(category);
    }
}
