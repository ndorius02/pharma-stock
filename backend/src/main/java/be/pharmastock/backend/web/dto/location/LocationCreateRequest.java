package be.pharmastock.backend.web.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LocationCreateRequest(

        @NotBlank(message = "Location name is required")
        @Size(max = 150, message = "Location name must be <= 150 characters")
        String name,
        String description


) {  }
