package zyx.franco.sports_events_api.team;

import zyx.franco.sports_events_api.player.Player;
import zyx.franco.sports_events_api.sport.SportCategory;

import java.time.LocalDate;
import java.util.List;

public record TeamResponseDTO(
        Long id,
        String name,
        LocalDate recordDate,
        Boolean isActive,
        String dependencyName,
        String sportName,
        SportCategory sportCategory,
        String eventName,
        List<Player> players
) {
}
