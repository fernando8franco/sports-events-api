package zyx.franco.sports_events_api.dependency;

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
@RequestMapping("/v1/dependencies")
public class DependencyController {
    private final DependencyService dependencyService;

    public DependencyController(DependencyService dependencyService) {
        this.dependencyService = dependencyService;
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

    @GetMapping
    public ResponseEntity<List<DependencyResponseDTO>> findAllDependencies(
            @PageableDefault(page = 1, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<DependencyResponseDTO> dependencyResponseDTOS = dependencyService.findAllDependencies(pageable, sortBy, ascending);
        return ResponseEntity.ok(dependencyResponseDTOS.getContent());
    }

    @GetMapping("/{dependencyId}")
    public ResponseEntity<DependencyDTO> findDependencyById(@PathVariable Integer dependencyId) {
        DependencyDTO dependency = dependencyService.findById(dependencyId);

        return ResponseEntity.ok(dependency);
    }

    @PutMapping("/{dependencyId}")
    public ResponseEntity<Void> updateDependency(
            @PathVariable Integer dependencyId,
            @Valid @RequestBody DependencyDTO dependencyDTO
    ) {
        dependencyService.findById(dependencyId);

        Dependency updateDependency = DependencyMapper.toDependencyEntity(dependencyDTO);
        updateDependency.setId(dependencyId);

        dependencyService.updateDependency(updateDependency);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{dependencyId}")
    public ResponseEntity<Void> deleteDependency(
            @PathVariable Integer dependencyId
    ) {
        dependencyService.findById(dependencyId);

        dependencyService.deleteDependency(dependencyId);

        return ResponseEntity.noContent().build();
    }
}
