package zyx.franco.sports_events_api.dependency;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/dependencies")
public class DependencyController {
    private final DependencyService dependencyService;

    public DependencyController(DependencyService dependencyService) {
        this.dependencyService = dependencyService;
    }

    @GetMapping("/{dependencyId}")
    public ResponseEntity<Dependency> findDependencyById(@PathVariable Integer dependencyId) {
        Dependency dependency = dependencyService.findById(dependencyId);

        return (dependency != null)
                ? ResponseEntity.ok(dependency)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveDependency(
            @Valid @RequestBody DependencyDTO dependencyDTO,
            UriComponentsBuilder ucb
    ) {
        Integer dependencyId = dependencyService.saveDependency(dependencyDTO);
        URI location = ucb
                .path("/v1/dependencies/{id}")
                .buildAndExpand(dependencyId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
