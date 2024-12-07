package zyx.franco.sports_events_api.dependency;

import jakarta.validation.constraints.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

public record DependencyDTO(
        @Size(min = 5, max = 150, message = "The dependency name must be between 5 and 150 characters")
        String name,
        @NotNull(message = "The category must not be null")
        DependencyCategory category
) {
}
