package zyx.franco.sports_events_api.dependency;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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

    @GetMapping("/{dependencyId}")
    public ResponseEntity<Dependency> findDependencyById(@PathVariable Integer dependencyId) {
        Dependency dependency = dependencyService.findById(dependencyId);

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
}
