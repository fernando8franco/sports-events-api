package zyx.franco.sports_events_api.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Integer saveEvent(EventDTO eventDTO) {
        Event event = EventMapper.toEvent(eventDTO);
        Event eventSaved = eventRepository.save(event);
        return eventSaved.getId();
    }

    public Page<Event> findAllEvents(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                sort
        );

        return eventRepository.findAll(pageable);
    }

    public Event findEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    public void updateEvent(Integer id, EventDTO eventDTO) {
        findEventById(id);

        Event eventUpdate = EventMapper.toEvent(id, eventDTO);

        eventRepository.save(eventUpdate);
    }

    public void deleteEvent(Integer id) {
        findEventById(id);

        eventRepository.deleteById(id);
    }
}
