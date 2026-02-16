package be.pharmastock.backend.web.controller;

import be.pharmastock.backend.domain.model.Location;
import be.pharmastock.backend.service.LocationService;
import be.pharmastock.backend.web.dto.location.LocationCreateRequest;
import be.pharmastock.backend.web.dto.location.LocationResponse;
import be.pharmastock.backend.web.dto.location.LocationUpdateRequest;
import be.pharmastock.backend.web.mapper.LocationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor

public class LocationController {
    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationResponse create(@Valid @RequestBody LocationCreateRequest request) {
        Location entity = LocationMapper.toEntity(request);
        Location created = locationService.create(entity);
        return LocationMapper.toResponse(created);
    }

    @GetMapping
    public Page<LocationResponse> list(@PageableDefault(size = 10) Pageable pageable){
        return locationService.listActive(pageable)
                .map(LocationMapper::toResponse);
    }

    @GetMapping("/{id}")
    public LocationResponse getActive(@PathVariable Long id){
        return LocationMapper.toResponse(locationService.getActive(id));
    }

    @PatchMapping("/{id}")
    public LocationResponse update(@PathVariable Long id, @Valid @RequestBody LocationUpdateRequest request){
        Location input = new Location();
        input.setName(request.name());
        input.setDescription(request.description());
        Location updated = locationService.update(id, input);
        return LocationMapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        locationService.softDelete(id);
    }

}
