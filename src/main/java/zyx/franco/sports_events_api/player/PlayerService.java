package zyx.franco.sports_events_api.player;

import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.team.Team;
import zyx.franco.sports_events_api.team.TeamService;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
    }

    public void saveAllPlayers(List<PlayerDTO> playerDTOS, Team team) {
        List<Player> players = playerDTOS.stream()
                .map(playerDTO -> PlayerMapper.toPlayerEntity(playerDTO, team))
                .toList();

        playerRepository.saveAll(players);
    }
}
