package zyx.franco.sports_events_api.dependency;

public record DependencyResponseDTO(
        Integer id,
        String name,
        DependencyCategory category
) {
}
