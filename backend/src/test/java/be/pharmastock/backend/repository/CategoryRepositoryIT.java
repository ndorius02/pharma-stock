package be.pharmastock.backend.repository;

import be.pharmastock.backend.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest

public class CategoryRepositoryIT {
    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");


    @DynamicPropertySource
    static void registerProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void existsByNameIgnoreCaseAndActiveTrue_shouldReturnTrue_whenSaved() {
        Category c = new Category();
        c.setName("Vitamins");
        c.setDescription("desc");
        c.setActive(true);

        categoryRepository.saveAndFlush(c);

        assertTrue(categoryRepository.existsByNameIgnoreCaseAndActiveTrue("vitamins"));
    }
}



