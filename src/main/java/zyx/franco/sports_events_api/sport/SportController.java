package zyx.franco.sports_events_api.sport;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/sports")
public class SportController {
    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping
    public ResponseEntity<Void> saveSport(
            @Valid @RequestBody SportDTO sportDTO,
            UriComponentsBuilder ucb
    ) {
        Integer sportId = sportService.saveSport(sportDTO);
        URI location = ucb
                .path("/v1/sports/{id}")
                .buildAndExpand(sportId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
