package be.pharmastock.backend.web.dto.location;

import java.time.OffsetDateTime;


public record LocationResponse(
        Long id,
        String name,
        String description,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
