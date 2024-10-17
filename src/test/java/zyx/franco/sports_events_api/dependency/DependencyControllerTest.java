package zyx.franco.sports_events_api.dependency;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DependencyControllerTest {
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
    void ShouldSaveOneDependency() {
        DependencyDTO dependencyDTO = new DependencyDTO(
                "Institute of Technology",
                DependencyCategory.UNIVERSITY
        );
        System.out.println(dependencyDTO.toString());

        ResponseEntity<Void> createResponse = restTemplate.postForEntity(
                "/v1/dependencies",
                dependencyDTO,
                Void.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        URI location = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(
                location,
                String.class
        );

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Integer id = documentContext.read("$.id");
        String name = documentContext.read("$.name");

        assertNotNull(id);
        assertEquals(dependencyDTO.name(), name);
    }
}