package zyx.franco.sports_events_api.team;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zyx.franco.sports_events_api.event.EventDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Void> saveDependency(
            @Valid @RequestBody TeamDTO teamDTO,
            UriComponentsBuilder ucb
    ) {
        Long teamId = teamService.saveTeam(teamDTO);
        URI location = ucb
                .path("/v1/teams/{id}")
                .buildAndExpand(teamId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> findAllTeams(
            @PageableDefault(page = 1, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<TeamResponseDTO> teams = teamService.findAllTeams(pageable, sortBy, ascending);
        return ResponseEntity.ok(teams.getContent());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> findTeamById(@PathVariable Long teamId) {
        TeamResponseDTO team = teamService.findTeamById(teamId);

        return ResponseEntity.ok(team);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Void> updateTeam(
            @PathVariable Long teamId,
            @Valid @RequestBody TeamUpdateDTO teamDTO
    ) {
        teamService.updateTeam(teamId, teamDTO);

        return ResponseEntity.noContent().build();
    }
}
