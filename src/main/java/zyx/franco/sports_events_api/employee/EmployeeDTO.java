package zyx.franco.sports_events_api.employee;

import jakarta.validation.constraints.*;
import zyx.franco.sports_events_api.person.Gender;

import java.time.LocalDate;

public record EmployeeDTO(
        @NotNull(message = "The name must not be null")
        @Size(min = 8, max = 8, message = "The account number must be 8 characters")
        String accountNumber,

        @NotEmpty(message = "The first name must not be empty")
        @Size(max = 75, message = "The first name must be 75 characters max")
        String firstName,

        @NotEmpty(message = "The last name must not be empty")
        @Size(max = 75, message = "The last name must be 75 characters max")
        String lastName,

        @NotEmpty(message = "The email must not be empty")
        @Size(max = 75, message = "The email must be 50 characters max")
        @Email(message = "The email must be valid")
        String email,

        @NotEmpty(message = "The phone number must not be empty")
        @Size(max = 15, message = "The phone number must be 15 characters max")
        String phone_number,

        @NotEmpty(message = "The birthday must not be empty")
        LocalDate birthday,

        @NotEmpty(message = "The gender must not be empty")
        Gender gender,

        @NotNull(message = "The is active must not be null")
        Boolean isActive,

        @NotEmpty(message = "The role must not be empty")
        Role role,

        @NotEmpty(message = "The dependency id must not be empty")
        @Positive(message = "The dependency id must positive number")
        @Max(value = Integer.MAX_VALUE, message = "The dependency id must not be greater than " + Integer.MAX_VALUE)
        Integer dependencyId
) {
}
