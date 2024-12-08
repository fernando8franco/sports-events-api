package zyx.franco.sports_events_api.dependency;

import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

@Service
public class DependencyService {
    private final DependencyRepository dependencyRepository;
    private final DependencyMapper dependencyMapper;

    public DependencyService(DependencyRepository dependencyRepository, DependencyMapper dependencyMapper) {
        this.dependencyRepository = dependencyRepository;
        this.dependencyMapper = dependencyMapper;
    }

    public Integer saveDependency(DependencyDTO dependencyDTO) {
        Dependency dependency = dependencyMapper.toDependencyEntity(dependencyDTO);
        Dependency dependencySaved = dependencyRepository.save(dependency);
        return dependencySaved.getId();
    }

    public DependencyDTO findById(Integer id) {
        return dependencyRepository.findById(id)
                .map(dependency -> new DependencyDTO(
                        dependency.getName(),
                        dependency.getCategory()
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Dependency not found with id: " + id));
    }
}
