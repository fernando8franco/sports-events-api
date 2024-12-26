package zyx.franco.sports_events_api.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Employee> findAllEmployees(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                sort
        );

        return employeeRepository.findAll(pageable);
    }

    public Employee findEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public void updateEmployee(UUID employeeId, EmployeeDTO employeeDTO) {
        findEmployeeById(employeeId);

        Dependency dependency = dependencyRepository.findById(employeeDTO.dependencyId())
                .orElseThrow(() -> new ResourceNotFoundException("Dependency not found with id: " + employeeDTO.dependencyId()));

        Employee updateEmployee = EmployeeMapper.toEmployeeEntity(employeeDTO, dependency);
        updateEmployee.setId(employeeId);

        employeeRepository.save(updateEmployee);
    }
}
