package zyx.franco.sports_events_api.sport;

public class SportMapper {

    public static Sport toSportEntity(SportDTO sportDTO) {
        if (sportDTO == null)
            throw new IllegalArgumentException("The sport should not be null");

        Sport sport = new Sport();
        sport.setName(sportDTO.name());
        sport.setCategory(sportDTO.category());
        sport.setNumPlayers(sportDTO.numPlayers());
        sport.setNumExtraPlayers(sportDTO.numExtraPlayers());
        sport.setHasCaptain(sportDTO.hasCaptain());
        sport.setActive(sportDTO.isActive());

        return sport;
    }

    public static SportDTO toSportDTO(Sport sport) {
        if (sport == null)
            throw new IllegalArgumentException("The sport should not be null");

        return new SportDTO(
                sport.getName(),
                sport.getCategory(),
                sport.getNumPlayers(),
                sport.getNumExtraPlayers(),
                sport.getHasCaptain(),
                sport.getActive()
        );
    }

}
