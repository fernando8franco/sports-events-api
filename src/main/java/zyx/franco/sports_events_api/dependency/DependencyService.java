package zyx.franco.sports_events_api.dependency;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

@Service
public class DependencyService {
    private final DependencyRepository dependencyRepository;

    public DependencyService(DependencyRepository dependencyRepository) {
        this.dependencyRepository = dependencyRepository;
    }

    public Integer saveDependency(DependencyDTO dependencyDTO) {
        Dependency dependency = DependencyMapper.toDependency(dependencyDTO);
        Dependency dependencySaved = dependencyRepository.save(dependency);
        return dependencySaved.getId();
    }

    public Page<Dependency> findAllDependencies(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                sort
        );

        return dependencyRepository.findAll(pageable);
    }

    public Dependency findDependencyById(Integer id) {
        return dependencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependency not found with id: " + id));
    }

    public void updateDependency(Integer id, DependencyDTO dependencyDTO) {
        findDependencyById(id);

        Dependency dependencyUpdate = DependencyMapper.toDependency(id, dependencyDTO);

        dependencyRepository.save(dependencyUpdate);
    }

    public void deleteDependency(Integer id) {
        findDependencyById(id);

        dependencyRepository.deleteById(id);
    }
}
