package org.silverstar.category.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.silverstar.category.acceptance.db.DataLoader;
import org.silverstar.category.acceptance.db.DatabaseCleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("intg-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    private DataLoader dataLoader;

    @BeforeEach
    public void setUp() {
        databaseCleanup.execute();
        dataLoader.loadData();
    }

}
