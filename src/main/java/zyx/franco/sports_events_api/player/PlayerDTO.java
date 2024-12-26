package zyx.franco.sports_events_api.player;

import jakarta.validation.constraints.*;
import org.springframework.data.domain.Sort;
import zyx.franco.sports_events_api.employee.Role;
import zyx.franco.sports_events_api.person.Gender;

import java.time.LocalDate;

public record PlayerDTO(
        @NotNull(message = "The name must not be null")
        @Size(min = 6, max = 6, message = "The account number must be 8 characters")
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
        String phoneNumber,

        @NotNull(message = "The birthday must not be null")
        LocalDate birthday,

        @NotNull(message = "The gender must not be null")
        Gender gender,

        @NotNull(message = "The is active must not be null")
        Boolean isActive,

        @NotNull(message = "The semester must not be null")
        @Positive(message = "The semester must be positive number")
        @Max(value = 15, message = "The semester must not be greater than 15")
        Short semester,

        @NotNull(message = "The group must not be null")
        @Positive(message = "The group must be positive number")
        @Max(value = Short.MAX_VALUE, message = "The group must not be greater than " + Short.MAX_VALUE)
        Short group,

        @NotNull(message = "The is captain must not be null")
        Boolean isCaptain
) {
}
