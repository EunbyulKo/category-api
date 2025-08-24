package org.silverstar.category.acceptance.repository;

import org.junit.jupiter.api.BeforeEach;
import org.silverstar.category.acceptance.db.DatabaseCleanup;
import org.silverstar.category.repository.CategoryRepositoryImpl;
import org.silverstar.category.repository.CategoryRepositoryTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({CategoryRepositoryImpl.class, DatabaseCleanup.class})
class CategoryRepositoryImplIntgTest extends CategoryRepositoryTemplate {

    @Autowired
    DatabaseCleanup databaseCleanup;

    @BeforeEach
    public void setUp() {
        databaseCleanup.execute();
    }

}