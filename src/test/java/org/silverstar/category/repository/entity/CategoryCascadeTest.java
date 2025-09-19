package org.silverstar.category.repository.entity;

import org.junit.jupiter.api.Test;
import org.silverstar.category.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CategoryCascadeTest extends CategoryTemplate {

    @Autowired
    private TestEntityManager em;

    @Test
    void Category_저장시_Image도_함께저장된다() {
        // given (도메인 → 엔티티 변환)
        Category category = Category.create(null, name, parentId, state, images);
        CategoryEntity categoryEntity = CategoryEntity.createCategory(category);

        // when
        em.persist(categoryEntity);
        em.flush();
        em.clear();

        // then
        CategoryEntity found = em.find(CategoryEntity.class, categoryEntity.getId());
        assertThat(found.getImages()).hasSize(2);
    }

    @Test
    void Category에서_Image제거시_DB에서도삭제된다() {
        Category category = Category.create(null, name, parentId, state, images);
        CategoryEntity categoryEntity = CategoryEntity.createCategory(category);

        em.persist(categoryEntity);
        em.flush();
        em.clear();

        // when
        CategoryEntity found = em.find(CategoryEntity.class, categoryEntity.getId());
        found.getImages().remove(0); // orphanRemoval = true

        em.flush();
        em.clear();

        // then
        CategoryEntity afterUpdate = em.find(CategoryEntity.class, categoryEntity.getId());
        assertThat(afterUpdate.getImages()).hasSize(1);
    }
}
