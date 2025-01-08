package zyx.franco.sports_events_api.team;

import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.event.Event;

import java.time.LocalDate;

public class TeamMapper {

    public static Team toTeam(
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

    public static Team toTeam(
            Long id,
            TeamUpdateDTO teamUpdateDTO,
            LocalDate recordDate,
            DependencySport dependencySport,
            Event event
    ) {
        if (teamUpdateDTO == null)
            throw new IllegalArgumentException("The team should not be null");

        return new Team(
                id,
                teamUpdateDTO.name(),
                recordDate,
                teamUpdateDTO.isActive(),
                dependencySport,
                event,
                null
        );
    }

    public static TeamResponseDTO toTeamResponseDTO(Team team) {
        if (team == null)
            throw new IllegalArgumentException("The team should not be null");

        return new TeamResponseDTO(
                team.getId(),
                team.getName(),
                team.getRecordDate(),
                team.getActive(),
                team.getDependencySport().getDependency().getName(),
                team.getDependencySport().getSport().getName(),
                team.getDependencySport().getSport().getCategory(),
                team.getEvent().getName(),
                team.getPlayers()
        );
    }
}
