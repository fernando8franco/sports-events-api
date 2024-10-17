package zyx.franco.sports_events_api.dependency;

import org.springframework.stereotype.Service;

@Service
public class DependencyMapper {

    public Dependency toDependencyEntity(DependencyDTO dependencyDTO) {
        if (dependencyDTO == null)
            throw new IllegalArgumentException("The dependency should not be null");

        return new Dependency(
                null,
                dependencyDTO.name(),
                dependencyDTO.category()
        );
    }

}
