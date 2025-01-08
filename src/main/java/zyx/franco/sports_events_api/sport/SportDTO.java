package zyx.franco.sports_events_api.sport;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SportDTO(
        @NotNull(message = "The name must not be null")
        @Size(min = 1, max = 150, message = "The sport name must be between 1 and 150 characters")
        String name,

        @NotNull(message = "The category must not be null")
        SportCategory category,

        @NotNull(message = "The number of players must not be null")
        @Max(value = 500, message = "The number of players must be in range of 0-500")
        @Min(value = 1, message = "The number of players must be in range of 1-500")
        Integer numPlayers,

        @NotNull(message = "The extra number of players must not be null")
        @Max(value = 500, message = "The extra number of players must be in range of 0-500")
        @Min(value = 0, message = "The extra number of players must be in range of 0-500")
        Integer numExtraPlayers,

        @NotNull(message = "The captain must not be null")
        Boolean hasCaptain,

        @NotNull(message = "The is active must not be null")
        Boolean isActive
) {
}
