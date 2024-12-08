package zyx.franco.sports_events_api.dependency;

import org.springframework.stereotype.Service;

@Service
public class DependencyMapper {

    public Dependency toDependencyEntity(DependencyDTO dependencyDTO) {
        if (dependencyDTO == null)
            throw new IllegalArgumentException("The dependencyDTO should not be null");

        Dependency dependency = new Dependency();
        dependency.setName(dependencyDTO.name());
        dependency.setCategory(dependencyDTO.category());
        return dependency;
    }

    public static DependencyDTO toDependencyDTO(Dependency dependency) {
        if (dependency == null)
            throw new IllegalArgumentException("The dependency should not be null");

        return new DependencyDTO(
                dependency.getName(),
                dependency.getCategory()
        );
    }

}
