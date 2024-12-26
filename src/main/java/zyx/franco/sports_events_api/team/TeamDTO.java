package zyx.franco.sports_events_api.team;

import jakarta.validation.constraints.*;
import zyx.franco.sports_events_api.player.Player;

import java.time.LocalDate;
import java.util.List;

public record TeamDTO(
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

        @NotEmpty(message = "The players must not be null")
        List<Player> players
) {
}
