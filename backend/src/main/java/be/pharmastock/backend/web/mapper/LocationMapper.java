package be.pharmastock.backend.web.mapper;

import be.pharmastock.backend.domain.model.Location;
import be.pharmastock.backend.web.dto.location.LocationResponse;
import be.pharmastock.backend.web.dto.location.LocationUpdateRequest;
import be.pharmastock.backend.web.dto.location.LocationCreateRequest;

public final class LocationMapper {

    private LocationMapper() {}

    public static Location toEntity(LocationCreateRequest req) {
        Location location = new Location();
        location.setName(req.name());
        location.setDescription(req.description());
        return location;
    }

    public static void applyUpdates(Location existing, LocationUpdateRequest req) {
        if (req.name() != null) {
            existing.setName(req.name());
        }

        if (req.description() != null) {
            existing.setDescription(req.description());
        }

    }

    public static LocationResponse toResponse(Location location) {
        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getDescription(),
                location.getActive(),
                location.getCreatedAt(),
                location.getUpdatedAt()
        );
    }
}
