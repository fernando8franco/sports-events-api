package zyx.franco.sports_events_api.dependency;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

    public Page<DependencyDTO> findAllDependencies(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        Page<Dependency> dependencyPage = dependencyRepository.findAll(pageable);

        return dependencyPage.map(DependencyMapper::toDependencyDTO);
    }

    public DependencyDTO findById(Integer id) {
        return dependencyRepository.findById(id)
                .map(DependencyMapper::toDependencyDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Dependency not found with id: " + id));
    }
}
