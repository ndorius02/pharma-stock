package be.pharmastock.backend.service;

import be.pharmastock.backend.domain.model.Category;
import be.pharmastock.backend.exception.ConflictException;
import be.pharmastock.backend.exception.NotFoundException;
import be.pharmastock.backend.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void create_shouldThrowConflict_whenNameAlreadyExists() {
        Category input = new Category();
        input.setName("Painkillers");
        input.setDescription("desc");

        when(categoryRepository.existsByNameIgnoreCaseAndActiveTrue("Painkillers"))
                .thenReturn(true);

        assertThrows(ConflictException.class, () -> categoryService.create(input));

        verify(categoryRepository, never()).save(any());
    }

    @Test
    void getActive_shouldThrowNotFound_whenCategoryDoesNotExist() {
        when(categoryRepository.findByIdAndActiveTrue(99L))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryService.getActive(99L));
    }

    @Test
    void softDelete_shouldSetActiveFalse_andSave() {
        Category existing = new Category();
        existing.setId(1L);
        existing.setName("Cat");
        existing.setActive(true);

        when(categoryRepository.findByIdAndActiveTrue(1L))
                .thenReturn(Optional.of(existing));

        categoryService.softDelete(1L);

        assertFalse(existing.isActive()); // ou getActive() selon ton entity
        verify(categoryRepository).save(existing);
    }
}



