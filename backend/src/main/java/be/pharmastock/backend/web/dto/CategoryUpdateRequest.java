package be.pharmastock.backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryUpdateRequest(
        @NotBlank(message = "Category name is required")
        @Size(max = 150, message = "Category name must be <= 150 characters")
        String name,

        @Size(max = 500, message = "Description must be <= 500 characters")
        String description
) { }
