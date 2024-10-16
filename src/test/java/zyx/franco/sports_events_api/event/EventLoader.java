package zyx.franco.sports_events_api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("eventTest")
public class EventLoader implements CommandLineRunner {
    @Autowired
    EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            String name = "Event " + i;
            Event event = new Event(null, name, LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now());
            eventRepository.save(event);
        }
    }
}
