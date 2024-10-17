package zyx.franco.sports_events_api.dependency;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dependency")
public class DependencyController {
    private final DependencyService dependencyService;

    public DependencyController(DependencyService dependencyService) {
        this.dependencyService = dependencyService;
    }
}
