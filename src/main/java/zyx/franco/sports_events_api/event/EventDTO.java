package zyx.franco.sports_events_api.event;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventDTO(
        @NotBlank(message = "The event name must not be null or blank")
        @Size(min = 5, max = 150, message = "The event name must be between 5 and 200 characters")
        String name,
        @NotNull(message = "The start date must not be null")
        LocalDate startDate,
        @NotNull(message = "The end date must not be null")
        LocalDate endDate,
        @NotNull(message = "The inscription start date must not be null")
        LocalDate insStartDate,
        @NotNull(message = "The inscription end date must not be null")
        LocalDate insEndDate
) {
        @AssertTrue(message = "The end date must be equal to or after the start date")
        public boolean isEndDateValid() {
                return !endDate.isBefore(startDate);
        }

        @AssertTrue(message = "The inscription end date must be equal to or after the inscription start date")
        public boolean isInsEndDateValid() {
                return !insEndDate.isBefore(insStartDate);
        }

        @AssertTrue(message = "The inscription start date and and inscription end date must be in between start date and end date")
        public boolean isInsStartDateAndInsEndDateValid() {
                return !insStartDate.isBefore(startDate) && !insEndDate.isAfter(endDate);
        }
}