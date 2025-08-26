package org.silverstar.category.repository;

import org.junit.jupiter.api.Test;
import org.silverstar.category.domain.Category;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class CategoryRepositoryTemplate extends CategoryTemplate {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Test
    void save_findById_Test() {
        //given
        Category category = Category.create(null, name, parentId, state);
        Category saved = categoryRepository.save(category);

        //when
        Category found = categoryRepository.findById(saved.getId()).get();

        //then
        assertEquals(1, found.getId());
    }

    @Test
    void save_findByParentId_Test() {
        //given
        Category category = Category.create(null, name, parentId, state);
        Category parent = categoryRepository.save(category);
        saveChildren(parent);
        saveChildren(parent);

        //when
        List<Category> children = categoryRepository.findByParentId(parent.getId());

        //then
        assertEquals(2, children.size());
    }

    @Test
    void save_findChildIds_Test() {
        //given
        Category category = Category.create(null, name, parentId, state);
        Category parent = categoryRepository.save(category);
        Category child1 = saveChildren(parent);
        Category child2 = saveChildren(parent);

        //when
        List<Long> ids = categoryRepository.findChildIds(parent.getId());

        //then
        assertEquals(2, ids.size());
        assertTrue(ids.contains(child1.getId()));
        assertTrue(ids.contains(child2.getId()));
    }

    private Category saveChildren(Category parent) {
        Category childCategory = Category.create(null, name, parent.getId(), state);
        return categoryRepository.save(childCategory);
    }


}