package zyx.franco.sports_events_api.event;

import org.springframework.stereotype.Service;

@Service
public class EventMapper {

    public Event toEventEntity(EventDTO eventDTO) {
        if (eventDTO == null)
            throw new NullPointerException("The event should not be null");

        Event event = new Event();
        event.setId(null);
        event.setName(eventDTO.name());
        event.setStartDate(eventDTO.startDate());
        event.setEndDate(eventDTO.endDate());
        event.setInsStartDate(eventDTO.insStartDate());
        event.setInsEndDate(eventDTO.insEndDate());

        return event;
    }

}