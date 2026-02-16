package be.pharmastock.backend.repository;

import be.pharmastock.backend.domain.model.Category;
import be.pharmastock.backend.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByNameIgnoreCase(String name);
    Page<Location> findAllByActiveTrue(Pageable pageable);
    boolean existsByNameIgnoreCaseAndActiveTrue(String name);
    Optional<Location> findByIdAndActiveTrue(Long id);
}
