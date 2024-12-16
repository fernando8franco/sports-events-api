package zyx.franco.sports_events_api.event;

import java.time.LocalDate;

public record EventResponseDTO(
        Integer id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate insStartDate,
        LocalDate insEndDate
) {
}
