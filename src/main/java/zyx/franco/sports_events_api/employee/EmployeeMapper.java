package zyx.franco.sports_events_api.employee;

import zyx.franco.sports_events_api.dependency.Dependency;

import java.util.UUID;

public class EmployeeMapper {

    public static Employee toEmployee(EmployeeDTO employeeDTO, Dependency dependency) {
        if (employeeDTO == null)
            throw new IllegalArgumentException("The employee should not be null");

        return new Employee(
                null,
                employeeDTO.accountNumber(),
                employeeDTO.firstName(),
                employeeDTO.lastName(),
                employeeDTO.email(),
                employeeDTO.phoneNumber(),
                employeeDTO.birthday(),
                employeeDTO.gender(),
                "null",
                employeeDTO.isActive(),
                employeeDTO.role(),
                dependency
        );
    }

    public static Employee toEmployee(UUID id, EmployeeDTO employeeDTO, Dependency dependency) {
        if (employeeDTO == null)
            throw new IllegalArgumentException("The employee should not be null");

        return new Employee(
                id,
                employeeDTO.accountNumber(),
                employeeDTO.firstName(),
                employeeDTO.lastName(),
                employeeDTO.email(),
                employeeDTO.phoneNumber(),
                employeeDTO.birthday(),
                employeeDTO.gender(),
                "null",
                employeeDTO.isActive(),
                employeeDTO.role(),
                dependency
        );
    }
}
