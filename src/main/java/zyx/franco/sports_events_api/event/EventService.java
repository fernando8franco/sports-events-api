package zyx.franco.sports_events_api.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public Event saveEvent(EventDTO eventDTO) {
        Event event = eventMapper.toEventEntity(eventDTO);
        return eventRepository.save(event);
    }

    public Event findById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Page<Event> findAllEvents(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        return eventRepository.findAll(pageable);
    }

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}
