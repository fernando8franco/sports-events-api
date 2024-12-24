package zyx.franco.sports_events_api.employee;

import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.dependency.DependencyRepository;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DependencyRepository dependencyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DependencyRepository dependencyRepository) {
        this.employeeRepository = employeeRepository;
        this.dependencyRepository = dependencyRepository;
    }

    public UUID saveEmployee(EmployeeDTO employeeDTO) {
        Dependency dependency = dependencyRepository.findById(employeeDTO.dependencyId())
                .orElseThrow(() -> new ResourceNotFoundException("Dependency not found with id: " + employeeDTO.dependencyId()));

        Employee employee = EmployeeMapper.toEmployeeEntity(employeeDTO, dependency);
        Employee employeeSaved = employeeRepository.save(employee);
        return employeeSaved.getId();
    }
}
