package zyx.franco.sports_events_api.dependency;

import org.springframework.stereotype.Service;

@Service
public class DependencyMapper {

    public Dependency toDependencyEntity(DependencyDTO dependencyDTO) {
        if (dependencyDTO == null)
            throw new NullPointerException("The dependency should not be null");

        Dependency dependency = new Dependency();
        dependency.setName(dependencyDTO.name());
        dependency.setCategory(dependencyDTO.category());

        return dependency;
    }

}
