package be.pharmastock.backend.service;

import be.pharmastock.backend.domain.model.Category;
import be.pharmastock.backend.exception.ConflictException;
import be.pharmastock.backend.exception.NotFoundException;
import be.pharmastock.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        if (categoryRepository.existsByNameIgnoreCaseAndActiveTrue(category.getName())) {
            throw new ConflictException("Category name already exists: " + category.getName());
        }
        category.setId(null);
        category.setActive(true);
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Page<Category> listActive(Pageable pageable) {
        return categoryRepository.findByActiveTrue(pageable);
    }

    @Transactional(readOnly = true)
    public Category getActive(Long id) {
        return categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));
    }

    public Category update(Long id, Category input) {
        Category existing = getActive(id);

        if (!existing.getName().equalsIgnoreCase(input.getName())
                && categoryRepository.existsByNameIgnoreCaseAndActiveTrue(input.getName())) {
            throw new ConflictException("Category name already exists: " + input.getName());
        }

        existing.setName(input.getName());
        existing.setDescription(input.getDescription());
        return categoryRepository.save(existing);
    }

    public void softDelete(Long id) {
        Category existing = getActive(id);
        existing.setActive(false);
        categoryRepository.save(existing);
    }
}
