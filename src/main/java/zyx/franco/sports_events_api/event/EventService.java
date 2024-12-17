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

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
    }

    public Integer saveEvent(EventDTO eventDTO) {
        Event event = EventMapper.toEventEntity(eventDTO);
        Event eventSaved = eventRepository.save(event);
        return eventSaved.getId();
    }

    public Page<EventResponseDTO> findAllEvents(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                sort
        );

        Page<Event> eventPage = eventRepository.findAll(pageable);

        return eventPage.map(EventMapper::toEventResponseDTO);
    }

    public EventDTO findById(Integer id) {
        return eventRepository.findById(id)
                .map(EventMapper::toEventDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}
