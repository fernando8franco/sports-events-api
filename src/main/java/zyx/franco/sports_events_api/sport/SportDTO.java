package zyx.franco.sports_events_api.sport;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SportDTO(
        @Size(min = 1, max = 150, message = "The dependency name must be between 5 and 150 characters")
        String name,
        @NotNull(message = "The category must not be null")
        SportCategory category,
        @Max(value = 500, message = "The number of players must be in range of 0-500")
        @Min(value = 0, message = "The number of players must be in range of 0-500")
        Integer numPlayers,
        @Max(value = 500, message = "The number of players must be in range of 0-500")
        @Min(value = 0, message = "The number of players must be in range of 0-500")
        Integer numExtraPlayers,
        @NotNull(message = "The captain must not be null")
        Boolean hasCaptain,
        @NotNull(message = "The is active must not be null")
        Boolean isActive
) {
}
