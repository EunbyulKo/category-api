package org.silverstar.category.repository.jpa;

import org.silverstar.category.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByParentId(Long parentId);

    @Query("SELECT c.id FROM CategoryEntity c WHERE c.parentId = :parentId")
    List<Long> findChildIds(Long parentId);
}