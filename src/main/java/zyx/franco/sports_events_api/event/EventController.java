package zyx.franco.sports_events_api.event;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> findById(@PathVariable Integer eventId) {
        EventDTO event = eventService.findById(eventId);

        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<Void> saveEvent(@Valid @RequestBody EventDTO eventDTO, UriComponentsBuilder ucb) {
        Integer eventId = eventService.saveEvent(eventDTO);
        URI location = ucb
                .path("v1/events/{id}")
                .buildAndExpand(eventId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> findAllEvents(
            @PageableDefault(page = 1, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<EventResponseDTO> pageEvents = eventService.findAllEvents(pageable, sortBy, ascending);
        return ResponseEntity.ok(pageEvents.getContent());
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Void> updateEvent(
            @PathVariable Integer eventId,
            @Valid @RequestBody EventDTO eventDTO
    ) {
        eventService.findById(eventId);

        Event updateEvent = EventMapper.toEventEntity(eventDTO);
        updateEvent.setId(eventId);

        eventService.updateEvent(updateEvent);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer eventId) {
        eventService.findById(eventId);

        eventService.deleteEvent(eventId);

        return ResponseEntity.noContent().build();
    }
}
