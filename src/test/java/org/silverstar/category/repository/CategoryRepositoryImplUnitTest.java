package org.silverstar.category.repository;

import org.silverstar.category.common.config.QuerydslConfig;
import org.silverstar.category.repository.dsl.CategoryQueryRepository;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import({CategoryRepositoryImpl.class, CategoryQueryRepository.class, QuerydslConfig.class})
class CategoryRepositoryImplUnitTest extends CategoryRepositoryTemplate {

}