package zyx.franco.sports_events_api.team;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.dependency_sport.DependencySportService;
import zyx.franco.sports_events_api.event.Event;
import zyx.franco.sports_events_api.event.EventService;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;
import zyx.franco.sports_events_api.player.Player;
import zyx.franco.sports_events_api.player.PlayerMapper;
import zyx.franco.sports_events_api.player.PlayerRepository;
import zyx.franco.sports_events_api.player.PlayerService;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final DependencySportService dependencySportService;
    private final EventService eventService;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository, DependencySportService dependencySportService, EventService eventService) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.dependencySportService = dependencySportService;
        this.eventService = eventService;
    }

    @Transactional
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

        List<Player> players = teamDTO.players().stream()
                .map(playerDTO -> PlayerMapper.toPlayerEntity(playerDTO, team))
                .toList();

        playerRepository.saveAll(players);

        return savedTeam.getId();
    }

    public Team findTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }
}
