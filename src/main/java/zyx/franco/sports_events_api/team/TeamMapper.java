package zyx.franco.sports_events_api.team;

import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.event.Event;

import java.time.LocalDate;

public class TeamMapper {

    public static Team toTeamEntity(
            TeamDTO teamDTO,
            LocalDate recordDate,
            Boolean isActive,
            DependencySport dependencySport,
            Event event
    ) {
        if (teamDTO == null)
            throw new IllegalArgumentException("The team should not be null");

        return new Team(
                null,
                teamDTO.name(),
                recordDate,
                isActive,
                dependencySport,
                event,
                null
        );
    }
}
