package be.pharmastock.backend.web.mapper;

import be.pharmastock.backend.domain.model.Category;
import be.pharmastock.backend.web.dto.CategoryCreateRequest;
import be.pharmastock.backend.web.dto.CategoryResponse;
import be.pharmastock.backend.web.dto.CategoryUpdateRequest;

public final class CategoryMapper {
    private CategoryMapper() {}

    public static Category toEntity(CategoryCreateRequest req) {
        Category c = new Category();
        c.setName(req.name());
        c.setDescription(req.description());
        return c;
    }

    public static void applyUpdates(Category existing, CategoryUpdateRequest req) {
        existing.setName(req.name());
        existing.setDescription(req.description());
    }

    public static CategoryResponse toResponse(Category c) {
        return new CategoryResponse(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.isActive(),
                c.getCreatedAt(),
                c.getUpdatedAt()
        );
    }
}
