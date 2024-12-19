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
        Dependency dependency = DependencyMapper.toDependencyEntity(dependencyDTO);
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

    public DependencyDTO findById(Integer id) {
        return dependencyRepository.findById(id)
                .map(DependencyMapper::toDependencyDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Dependency not found with id: " + id));
    }

    public void updateDependency(Dependency dependency) {
        dependencyRepository.save(dependency);
    }

    public void deleteDependency(Integer id) {
        dependencyRepository.deleteById(id);
    }
}
