package be.pharmastock.backend.web.dto.category;

import java.time.OffsetDateTime;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) { }

