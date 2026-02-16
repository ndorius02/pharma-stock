package be.pharmastock.backend.service;

import be.pharmastock.backend.domain.model.Location;
import be.pharmastock.backend.exception.ConflictException;
import be.pharmastock.backend.exception.NotFoundException;
import be.pharmastock.backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class LocationService {

    private final LocationRepository locationRepository;

    public Location create(Location location) {
        if(locationRepository.existsByNameIgnoreCaseAndActiveTrue(location.getName())){
            throw new ConflictException("Location name already exists");
        }
        location.setId(null); // id à null pour forcer la création d’une nouvelle entité en base de données.
        location.setActive(true);
        return locationRepository.save(location);
    }

    @Transactional(readOnly = true)
    public Location getActive(Long id) {
        return locationRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new NotFoundException("Location not found: " + id));
    }

    @Transactional(readOnly = true)
    public Page<Location> listActive(Pageable pageable) {
        return locationRepository.findAllByActiveTrue(pageable);
    }

    public Location update(Long id, Location location) {
        Location existing = getActive(id);
        if (!existing.getName().equalsIgnoreCase(location.getName())
                && locationRepository.existsByNameIgnoreCaseAndActiveTrue(location.getName())) {
            throw new ConflictException("Location name already exists: " + location.getName());
        }
        existing.setName(location.getName());
        existing.setDescription(location.getDescription());
        return locationRepository.save(existing);
    }

    public void softDelete(Long id) {
        Location existing = getActive(id);
        existing.setActive(false);
        locationRepository.save(existing);
    }

}

