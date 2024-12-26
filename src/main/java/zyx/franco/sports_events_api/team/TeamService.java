package zyx.franco.sports_events_api.team;

import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.dependency_sport.DependencySportService;
import zyx.franco.sports_events_api.event.Event;
import zyx.franco.sports_events_api.event.EventService;

import java.time.LocalDate;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final DependencySportService dependencySportService;
    private final EventService eventService;

    public TeamService(TeamRepository teamRepository, DependencySportService dependencySportService, EventService eventService) {
        this.teamRepository = teamRepository;
        this.dependencySportService = dependencySportService;
        this.eventService = eventService;
    }

    public Long saveTeam(TeamDTO teamDTO) {
        DependencySport dependencySport = dependencySportService.findDependencyServiceById(teamDTO.dependencySportId());
        Event event = eventService.findEventById(teamDTO.eventId());

        Team team = TeamMapper.toTeamEntity(
                teamDTO,
                LocalDate.now(),
                true,
                dependencySport,
                event
        );

        Team savedTeam = teamRepository.save(team);
        return savedTeam.getId();
    }
}
