package zyx.franco.sports_events_api.dependency;

import jakarta.validation.constraints.*;

public record DependencyDTO(
        @NotBlank(message = "The dependency name must not be null or blank")
        @Size(min = 5, max = 150, message = "The dependency name must be between 5 and 200 characters")
        String name,
        DependencyCategory category
) {
}
