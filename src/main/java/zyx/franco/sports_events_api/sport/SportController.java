package zyx.franco.sports_events_api.sport;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Sport>> findAllSports(
            @PageableDefault(page = 1, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<Sport> sportPage = sportService.findAllSports(
                pageable,
                sortBy,
                ascending
        );
        return ResponseEntity.ok(sportPage.getContent());
    }

    @GetMapping("/{sportId}")
    public ResponseEntity<SportDTO> findSportById(
            @PathVariable Integer sportId
    ) {
        SportDTO sportDTO = sportService.findSportById(sportId);
        return ResponseEntity.ok(sportDTO);
    }

    @PutMapping("/{sportId}")
    public ResponseEntity<Void> updateSport(
            @PathVariable Integer sportId,
            @Valid @RequestBody SportDTO sportDTO
    ) {
        sportService.findSportById(sportId);

        Sport updateSport = SportMapper.toSportEntity(sportDTO);
        updateSport.setId(sportId);

        sportService.updateSport(updateSport);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sportId}")
    public ResponseEntity<Void> deleteSport(
            @PathVariable Integer sportId
    ) {
        sportService.findSportById(sportId);

        sportService.deleteSport(sportId);

        return ResponseEntity.noContent().build();
    }
}
