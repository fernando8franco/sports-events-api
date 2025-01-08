package zyx.franco.sports_events_api.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.dependency.DependencyService;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DependencyService dependencyService;

    public EmployeeService(EmployeeRepository employeeRepository, DependencyService dependencyService) {
        this.employeeRepository = employeeRepository;
        this.dependencyService = dependencyService;
    }

    public UUID saveEmployee(EmployeeDTO employeeDTO) {
        Dependency dependency = dependencyService.findDependencyById(employeeDTO.dependencyId());

        Employee employee = EmployeeMapper.toEmployee(employeeDTO, dependency);
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

    public void updateEmployee(UUID id, EmployeeDTO employeeDTO) {
        findEmployeeById(id);

        Dependency dependency = dependencyService.findDependencyById(employeeDTO.dependencyId());

        Employee updateEmployee = EmployeeMapper.toEmployee(id, employeeDTO, dependency);

        employeeRepository.save(updateEmployee);
    }

    public void deleteEmployee(UUID employeeId) {
        findEmployeeById(employeeId);

        employeeRepository.deleteById(employeeId);
    }
}
