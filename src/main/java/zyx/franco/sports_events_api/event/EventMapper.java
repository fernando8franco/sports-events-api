package zyx.franco.sports_events_api.event;

public class EventMapper {

    public static Event toEvent(EventDTO eventDTO) {
        if (eventDTO == null)
            throw new IllegalArgumentException("The event should not be null");

        Event event = new Event();
        event.setId(null);
        event.setName(eventDTO.name());
        event.setStartDate(eventDTO.startDate());
        event.setEndDate(eventDTO.endDate());
        event.setInsStartDate(eventDTO.insStartDate());
        event.setInsEndDate(eventDTO.insEndDate());

        return event;
    }

    public static Event toEvent(Integer id, EventDTO eventDTO) {
        if (eventDTO == null)
            throw new IllegalArgumentException("The event should not be null");

        Event event = new Event();
        event.setId(id);
        event.setName(eventDTO.name());
        event.setStartDate(eventDTO.startDate());
        event.setEndDate(eventDTO.endDate());
        event.setInsStartDate(eventDTO.insStartDate());
        event.setInsEndDate(eventDTO.insEndDate());

        return event;
    }

    public static EventDTO toEventDTO(Event event) {
        if (event == null)
            throw new IllegalArgumentException("The event should not be null");

        return new EventDTO(
                event.getName(),
                event.getStartDate(),
                event.getEndDate(),
                event.getInsStartDate(),
                event.getInsEndDate()
        );
    }
}
