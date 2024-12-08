package zyx.franco.sports_events_api.dependency;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
    public ResponseEntity<List<DependencyDTO>> findAllDependencies(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<DependencyDTO> dependencyDTOS = dependencyService.findAllDependencies(pageable, sortBy, ascending);
        return ResponseEntity.ok(dependencyDTOS.getContent());
    }

    @GetMapping("/{dependencyId}")
    public ResponseEntity<DependencyDTO> findDependencyById(@PathVariable Integer dependencyId) {
        DependencyDTO dependency = dependencyService.findById(dependencyId);

        return (dependency != null)
                ? ResponseEntity.ok(dependency)
                : ResponseEntity.notFound().build();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (!(ex.getCause() instanceof InvalidFormatException ifx))
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));

        if (!(ifx.getTargetType() != null && ifx.getTargetType().isEnum()))
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));

        return ResponseEntity.badRequest().body(Collections.singletonMap(
                "category",
                String.format("Invalid enum value: '%s'. The value must be one of: %s.",
                ifx.getValue(), Arrays.toString(ifx.getTargetType().getEnumConstants()))
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.badRequest().body(Collections.singletonMap("message", ex.getMessage()));
    }
}
