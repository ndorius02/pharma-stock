package be.pharmastock.backend.web.controller;

import be.pharmastock.backend.domain.model.Category;
import be.pharmastock.backend.service.CategoryService;
import be.pharmastock.backend.web.dto.CategoryCreateRequest;
import be.pharmastock.backend.web.dto.CategoryResponse;
import be.pharmastock.backend.web.dto.CategoryUpdateRequest;
import be.pharmastock.backend.web.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CategoryCreateRequest request) {
        Category entity = CategoryMapper.toEntity(request);
        Category created = categoryService.create(entity);
        return CategoryMapper.toResponse(created);
    }

    @GetMapping
    public Page<CategoryResponse> list(@PageableDefault(size = 10) Pageable pageable) {
        return categoryService.listActive(pageable)
                .map(CategoryMapper::toResponse);
    }

    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable Long id) {
        return CategoryMapper.toResponse(categoryService.getActive(id));
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id,
                                   @Valid @RequestBody CategoryUpdateRequest request) {

        // On crée un "input entity" minimal pour réutiliser le service actuel
        Category input = new Category();
        input.setName(request.name());
        input.setDescription(request.description());

        Category updated = categoryService.update(id, input);
        return CategoryMapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.softDelete(id);
    }
}
