package zyx.franco.sports_events_api.dependency;

import org.springframework.stereotype.Service;

@Service
public class DependencyService {
    private final DependencyRepository dependencyRepository;
    private final DependencyMapper dependencyMapper;

    public DependencyService(DependencyRepository dependencyRepository, DependencyMapper dependencyMapper) {
        this.dependencyRepository = dependencyRepository;
        this.dependencyMapper = dependencyMapper;
    }

    public Dependency findById(Integer id) {
        return dependencyRepository.findById(id).orElse(null);
    }

    public Integer saveDependency(DependencyDTO dependencyDTO) {
        Dependency dependency = dependencyMapper.toDependencyEntity(dependencyDTO);
        Dependency dependencySaved = dependencyRepository.save(dependency);
        return dependencySaved.getId();
    }
}
