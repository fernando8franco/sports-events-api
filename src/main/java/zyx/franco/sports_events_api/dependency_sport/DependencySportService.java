package zyx.franco.sports_events_api.dependency_sport;

import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

@Service
public class DependencySportService {
    private final DependencySportRepository dependencySportRepository;

    public DependencySportService(DependencySportRepository dependencySportRepository) {
        this.dependencySportRepository = dependencySportRepository;
    }

    public DependencySport findDependencyServiceById(Integer id) {
        return dependencySportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependency Sport not found with id: " + id));
    }
}
