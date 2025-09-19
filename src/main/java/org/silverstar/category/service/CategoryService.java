package org.silverstar.category.service;

import lombok.RequiredArgsConstructor;
import org.silverstar.category.domain.CategoryImage;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.silverstar.category.domain.Category;
import org.silverstar.category.domain.CategoryState;
import org.silverstar.category.controller.dto.CreateCategoryRequestDto;
import org.silverstar.category.controller.dto.UpdateCategoryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public List<Category> getCategoryByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    public List<Long> getChildIds(Long parentId) {
        return categoryRepository.findChildIds(parentId);
    }

    public Category createCategory(CreateCategoryRequestDto dto) {
        CategoryState state = CategoryState.create(dto.useYn(), dto.dispYn());
        List<CategoryImage> images = dto.imageUrl().stream().map(CategoryImage::create).toList();
        Category category = Category.create(null, dto.name(), dto.parentId(), state, images);
        return categoryRepository.create(category);
    }

    public Category updateCategory(UpdateCategoryRequestDto dto) {
        CategoryState state = CategoryState.create(dto.useYn(), dto.dispYn());
        List<CategoryImage> images = dto.imageUrl().stream().map(CategoryImage::create).toList();

        Category category = getCategory(dto.id());

        category.updateName(dto.name());
        category.updateParentId(dto.parentId());
        category.updateState(state);
        category.updateImages(images);

        return categoryRepository.update(category);
    }
}
