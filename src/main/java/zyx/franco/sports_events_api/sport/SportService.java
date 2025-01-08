package zyx.franco.sports_events_api.sport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zyx.franco.sports_events_api.exceptions.ResourceNotFoundException;

@Service
public class SportService {
    private final SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Integer saveSport(SportDTO sportDTO) {
        Sport sport = SportMapper.toSportEntity(sportDTO);
        Sport sportSaved = sportRepository.save(sport);
        return sportSaved.getId();
    }

    public Page<Sport> findAllSports(Pageable pageable, String sortBy, boolean ascending) {
        Sort sort = ascending
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        pageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                sort
        );

        return sportRepository.findAll(pageable);
    }

    public Sport findSportById(Integer id) {
        return sportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sport not found with id: " + id));
    }

    public void updateSport(Integer id, SportDTO sportDTO) {
        findSportById(id);

        Sport sportUpdate = SportMapper.toSportEntity(id, sportDTO);

        sportRepository.save(sportUpdate);
    }

    public void deleteSport(Integer id) {
        findSportById(id);

        sportRepository.deleteById(id);
    }
}
