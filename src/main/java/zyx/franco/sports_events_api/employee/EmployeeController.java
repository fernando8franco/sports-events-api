package zyx.franco.sports_events_api.employee;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
}
