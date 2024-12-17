package zyx.franco.sports_events_api.sport;

public record SportResponseDTO(
        Integer id,
        String name,
        SportCategory category,
        Integer numPlayers,
        Integer numExtraPlayers,
        Boolean hasCaptain,
        Boolean isActive
) {
}
