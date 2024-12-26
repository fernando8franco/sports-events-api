package zyx.franco.sports_events_api.team;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
