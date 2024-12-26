package zyx.franco.sports_events_api.team;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.event.Event;
import zyx.franco.sports_events_api.player.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "t_team")
public class Team {
    @Id
    @SequenceGenerator(
            name = "team_seq",
            sequenceName = "t_team_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_seq"
    )
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "dependencies_sports_id")
    @JsonManagedReference
    private DependencySport dependencySport;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonManagedReference
    private Event event;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private List<Player> players;

    public Team() {
    }

    public Team(Long id, String name, LocalDate recordDate, Boolean isActive, DependencySport dependencySport, Event event, List<Player> players) {
        this.id = id;
        this.name = name;
        this.recordDate = recordDate;
        this.isActive = isActive;
        this.dependencySport = dependencySport;
        this.event = event;
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public DependencySport getDependencySport() {
        return dependencySport;
    }

    public void setDependencySport(DependencySport dependencySport) {
        this.dependencySport = dependencySport;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(recordDate, team.recordDate) && Objects.equals(isActive, team.isActive) && Objects.equals(dependencySport, team.dependencySport) && Objects.equals(event, team.event) && Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, recordDate, isActive, dependencySport, event, players);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recordDate=" + recordDate +
                ", isActive=" + isActive +
                ", dependencySport=" + dependencySport +
                ", event=" + event +
                ", players=" + players +
                '}';
    }
}
