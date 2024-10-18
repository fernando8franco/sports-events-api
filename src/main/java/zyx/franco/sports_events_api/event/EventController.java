package zyx.franco.sports_events_api.event;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> findById(@PathVariable Integer eventId) {
        Event event = eventService.findById(eventId);
        return (event != null)
                ? ResponseEntity.ok(event)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveEvent(@Valid @RequestBody EventDTO eventDTO, UriComponentsBuilder ucb) {
        Event eventSaved = eventService.saveEvent(eventDTO);
        URI location = ucb
                .path("v1/events/{id}")
                .buildAndExpand(eventSaved.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<Event> pageEvents = eventService.findAllEvents(pageable, sortBy, ascending);
        return ResponseEntity.ok(pageEvents.getContent());
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Void> updateEvent(
            @PathVariable Integer eventId,
            @RequestBody EventDTO eventDTO
    ) {
        Event event = eventService.findById(eventId);

        if (event == null)
            return ResponseEntity.notFound().build();

        Event updateEvent = new Event();
        event.setId(eventId);
        event.setName(eventDTO.name());
        event.setStartDate(eventDTO.startDate());
        event.setEndDate(eventDTO.endDate());
        event.setInsStartDate(eventDTO.insStartDate());
        event.setEndDate(eventDTO.insEndDate());

        eventService.updateEvent(updateEvent);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer eventId) {
        Event event = eventService.findById(eventId);

        if (event == null)
            return ResponseEntity.notFound().build();

        eventService.deleteEvent(eventId);

        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(Collections.singletonMap("message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
