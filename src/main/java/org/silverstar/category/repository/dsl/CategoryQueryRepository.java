package org.silverstar.category.repository.dsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.silverstar.category.repository.entity.QCategoryEntity;
import org.silverstar.category.service.dto.CategoryPreviewDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;

    public List<CategoryPreviewDto> getPreviewChildren(Long parentId) {
        return queryFactory
                .select(Projections.constructor(
                        CategoryPreviewDto.class,
                        categoryEntity.id,
                        categoryEntity.name
                ))
                .from(categoryEntity)
                .where(
                        categoryEntity.parentId.eq(parentId)
                )
                .orderBy(categoryEntity.id.desc())
                .limit(500)
                .fetch();
    }

}
