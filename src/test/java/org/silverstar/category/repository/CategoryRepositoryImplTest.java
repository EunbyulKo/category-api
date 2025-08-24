package org.silverstar.category.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.category.domain.Category;
import org.silverstar.category.service.interfaces.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(CategoryRepositoryImpl.class)
class CategoryRepositoryImplTest extends CategoryTemplate {

    @Autowired
    CategoryRepository categoryRepository;

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

        Category childCategory1 = Category.create(null, name, parent.getId(), state);
        Category childCategory2 = Category.create(null, name, parent.getId(), state);
        categoryRepository.save(childCategory1);
        categoryRepository.save(childCategory2);

        //when
        List<Category> children = categoryRepository.findByParentId(parent.getId());

        //then
        assertEquals(2, children.size());
    }


}