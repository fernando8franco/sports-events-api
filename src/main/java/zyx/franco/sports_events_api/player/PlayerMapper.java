package zyx.franco.sports_events_api.player;

import zyx.franco.sports_events_api.team.Team;

public class PlayerMapper {

    public static Player toPlayerEntity(
            PlayerDTO playerDTO,
            Team team
    ) {
        if (playerDTO == null)
            throw new IllegalArgumentException("The dependency should not be null");

        return new Player(
                null,
                playerDTO.accountNumber(),
                playerDTO.firstName(),
                playerDTO.lastName(),
                playerDTO.email(),
                playerDTO.phoneNumber(),
                playerDTO.birthday(),
                playerDTO.gender(),
                "null",
                playerDTO.isActive(),
                playerDTO.semester(),
                playerDTO.group(),
                playerDTO.isCaptain(),
                team
        );
    }
}
