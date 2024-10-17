package zyx.franco.sports_events_api.event;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("eventTest")
class EventControllerTest {
    @Autowired
    EventService eventService;
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Test
    void connectionEstablished() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    @Rollback
    @DirtiesContext
    void shouldSaveOneEvent() {
        EventDTO eventDTO = new EventDTO(
                "Event Test",
                LocalDate.of(2024, 10,20),
                LocalDate.of(2024, 10,25),
                LocalDate.of(2024, 10,20),
                LocalDate.of(2024, 10,25)
        );

        ResponseEntity<Void> createResponse = restTemplate.postForEntity(
                "/v1/events",
                eventDTO,
                Void.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        URI location = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(
                location,
                String.class
        );

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String name = documentContext.read("$.name");

        assertNotNull(id);
        assertEquals(eventDTO.name(), name);
    }

    @Test
    @Rollback
    @DirtiesContext
    void shouldReturnBadRequestAndEventMustNotBeNullOrBlank () {
        EventDTO eventDTO = new EventDTO(
                null,
                LocalDate.of(2024, 10,20),
                LocalDate.of(2024, 10,25),
                LocalDate.of(2024, 10,20),
                LocalDate.of(2024, 10,25)
        );

        ResponseEntity<String> createResponse = restTemplate.postForEntity(
                "/v1/events",
                eventDTO,
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, createResponse.getStatusCode());
        DocumentContext documentContext = JsonPath.parse(createResponse.getBody());
        String error = documentContext.read("$.name");
        assertEquals("The event name must not be null or blank", error);
    }

    @Test
    @Rollback
    @DirtiesContext
    void shouldReturnBadRequestAndEventInsDateMustBeInBetween () {
        EventDTO eventDTO = new EventDTO(
                "Event 1",
                LocalDate.of(2024, 10,20),
                LocalDate.of(2024, 10,25),
                LocalDate.of(2024, 10,21),
                LocalDate.of(2024, 10,28)
        );

        ResponseEntity<String> createResponse = restTemplate.postForEntity(
                "/v1/events",
                eventDTO,
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, createResponse.getStatusCode());

        DocumentContext documentContext = JsonPath.parse(createResponse.getBody());
        String error = documentContext.read("$.insStartDateAndInsEndDateValid");
        assertEquals(
                "The inscription start date and and inscription end date must be in between start date and end date",
                error
        );
    }

    @Test
    @Rollback
    @DirtiesContext
    void shouldReturnAllEventsInPagesAndSorted() {
        ResponseEntity<String> getResponse = restTemplate.getForEntity(
                "/v1/events?page=1&size=5&sortBy=name&ascending=false",
                String.class
        );

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        int length = documentContext.read("$.length()");
        assertEquals(5, length);

        JSONArray names = documentContext.read("$..name");
        List<String> expectedNames = List.of("Event 4","Event 3","Event 2","Event 1","Event 0");
        assertEquals(expectedNames, names);
    }

    @Test
    @Rollback
    @DirtiesContext
    void shouldUpdateAnExistingEvent() {
        EventDTO updateEventDTO = new EventDTO(
                "Event Update",
                LocalDate.of(2024, 10,21),
                LocalDate.of(2024, 10,26),
                LocalDate.of(2024, 10,22),
                LocalDate.of(2024, 10,25)
        );
        HttpEntity<EventDTO> request = new HttpEntity<>(updateEventDTO);

        ResponseEntity<Void> updateResponse = restTemplate.exchange(
                "/v1/events/1",
                HttpMethod.PUT,
                request,
                Void.class
        );
        assertEquals(HttpStatus.NO_CONTENT, updateResponse.getStatusCode());

        ResponseEntity<String> getResponse = restTemplate.getForEntity(
                "/v1/events/1",
                String.class
        );

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

        String name = documentContext.read("$.name");
        assertEquals(updateEventDTO.name(), name);

        String startDate = documentContext.read("$.startDate");
        assertEquals(updateEventDTO.startDate().toString(), startDate);
        String endDate = documentContext.read("$.endDate");
        assertEquals(updateEventDTO.endDate().toString(), endDate);
        String insStartDate = documentContext.read("$.insStartDate");
        assertEquals(updateEventDTO.insStartDate().toString(), insStartDate);
        String insEndDate = documentContext.read("$.insEndDate");
        assertEquals(updateEventDTO.insEndDate().toString(), insEndDate);
    }

    @Test
    @Rollback
    @DirtiesContext
    void shouldDeleteAnExistingEvent() {
        ResponseEntity<Void> response = restTemplate.exchange(
                "/v1/events/1",
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        ResponseEntity<String> getResponse = restTemplate.getForEntity(
                "/v1/events/1",
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}