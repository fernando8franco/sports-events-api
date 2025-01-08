package zyx.franco.sports_events_api.dependency_sport;

import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.InvalidTeamSizeException;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

@Service
public class DependencySportService {
    private final DependencySportRepository dependencySportRepository;

    public DependencySportService(DependencySportRepository dependencySportRepository) {
        this.dependencySportRepository = dependencySportRepository;
    }

    public DependencySport findDependencySportById(Integer id) {
        return dependencySportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependency Sport not found with id: " + id));
    }

    public DependencySport findDependencySportByIdAndCheckPlayers(Integer id, Integer totalPlayers) {
        DependencySport dependencySport = dependencySportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependency Sport not found with id: " + id));

        int numPlayers = dependencySport.getSport().getNumPlayers();
        int numExtraPlayers = dependencySport.getSport().getNumExtraPlayers();

        if (totalPlayers < numPlayers || totalPlayers > numPlayers + numExtraPlayers)
            throw new InvalidTeamSizeException("The number of players in the team is invalid. Expected between "
                    + numPlayers + " and "
                    + (numPlayers + numExtraPlayers)
                    + ", but got " + totalPlayers);

        return dependencySport;
    }
}
