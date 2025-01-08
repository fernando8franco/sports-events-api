package zyx.franco.sports_events_api.team;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TeamUpdateDTO(
        @NotNull(message = "The name must not be null")
        @Size(min = 1, max = 150, message = "The team name must be between 1 and 150 characters")
        String name,

        @NotNull(message = "The dependency sport id must not be null")
        @Positive(message = "The dependency sport id must positive number")
        @Max(value = Integer.MAX_VALUE, message = "The dependency sport id must not be greater than " + Integer.MAX_VALUE)
        Integer dependencySportId,

        @NotNull(message = "The event id must not be null")
        @Positive(message = "The event id must positive number")
        @Max(value = Integer.MAX_VALUE, message = "The event id must not be greater than " + Integer.MAX_VALUE)
        Integer eventId,

        @NotNull(message = "The is active field must not be null")
        Boolean isActive
) {
}
