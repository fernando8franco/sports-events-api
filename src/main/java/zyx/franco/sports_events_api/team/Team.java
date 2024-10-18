package zyx.franco.sports_events_api.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.event.Event;
import zyx.franco.sports_events_api.player.Player;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_team")
public class Team {
    @Id
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "dependencies_sports_id")
    @JsonBackReference
    private DependencySport dependencySport;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
