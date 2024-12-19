package zyx.franco.sports_events_api.dependency;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DependencyDTO(
        @NotNull(message = "The name must not be null")
        @Size(min = 5, max = 150, message = "The dependency name must be between 5 and 150 characters")
        String name,
        @NotNull(message = "The category must not be null")
        DependencyCategory category
) {
}
