package zyx.franco.sports_events_api.team;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.dependency_sport.DependencySportService;
import zyx.franco.sports_events_api.event.Event;
import zyx.franco.sports_events_api.event.EventService;
import zyx.franco.sports_events_api.exceptions.InvalidTeamSizeException;
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
    private final DependencySportService dependencySportService;
    private final EventService eventService;
    private final PlayerService playerService;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository, DependencySportService dependencySportService, EventService eventService, PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.dependencySportService = dependencySportService;
        this.eventService = eventService;
        this.playerService = playerService;
    }

    @Transactional
    public Long saveTeam(TeamDTO teamDTO) {
        DependencySport dependencySport = dependencySportService.findDependencyServiceById(teamDTO.dependencySportId());

        int numPlayers = dependencySport.getSport().getNumPlayers();
        int numExtraPlayers = dependencySport.getSport().getNumExtraPlayers();
        int totalPlayers = teamDTO.players().size();

        if (totalPlayers < numPlayers || totalPlayers > numPlayers + numExtraPlayers)
            throw new InvalidTeamSizeException("The number of players in the team is invalid. Expected between "
                    + numPlayers + " and "
                    + (numPlayers + numExtraPlayers)
                    + ", but got " + totalPlayers);

        Event event = eventService.findEventById(teamDTO.eventId());

        Team team = TeamMapper.toTeamEntity(
                teamDTO,
                LocalDate.now(),
                true,
                dependencySport,
                event
        );

        Team savedTeam = teamRepository.save(team);

        playerService.saveAllPlayers(teamDTO.players(), savedTeam);

        return savedTeam.getId();
    }

    public Page<TeamResponseDTO> findAllTeams(Pageable pageable, String sortBy, boolean ascending) {
        switch (sortBy) {
            case "dependency_name":
                sortBy = "dependencySport_dependency_name";
                break;
            case "sport_name":
                sortBy = "dependencySport_sport_name";
                break;
            case "sport_category":
                sortBy = "dependencySport_sport_category";
                break;
        }

        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                sort
        );

        return teamRepository.findAll(pageable)
                .map(TeamMapper::toTeamResponseDTO);
    }

    public TeamResponseDTO findTeamById(Long id) {
        return teamRepository.findById(id)
                .map(TeamMapper::toTeamResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
    }
}
