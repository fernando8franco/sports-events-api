package zyx.franco.sports_events_api.employee;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Void> saveEmployee(
        @Valid @RequestBody EmployeeDTO employeeDTO,
        UriComponentsBuilder ucb
    ) {
        UUID employeeId = employeeService.saveEmployee(employeeDTO);
        URI location = ucb
                .path("/v1/employees/{id}")
                .buildAndExpand(employeeId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees(
            @PageableDefault(page = 1, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<Employee> employees = employeeService.findAllEmployees(pageable, sortBy, ascending);
        return ResponseEntity.ok(employees.getContent());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable UUID employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);

        return ResponseEntity.ok(employee);
    }
}
