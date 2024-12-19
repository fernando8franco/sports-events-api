package zyx.franco.sports_events_api.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import zyx.franco.sports_events_api.team.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate insStartDate;

    @Column(nullable = false)
    private LocalDate insEndDate;

    @OneToMany(mappedBy = "event")
    @JsonBackReference
    private List<Team> teams;

    public Event() {
    }

    public Event(Integer id, String name, LocalDate startDate, LocalDate endDate, LocalDate insStartDate, LocalDate insEndDate, List<Team> teams) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.insStartDate = insStartDate;
        this.insEndDate = insEndDate;
        this.teams = teams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getInsStartDate() {
        return insStartDate;
    }

    public void setInsStartDate(LocalDate insStartDate) {
        this.insStartDate = insStartDate;
    }

    public LocalDate getInsEndDate() {
        return insEndDate;
    }

    public void setInsEndDate(LocalDate insEndDate) {
        this.insEndDate = insEndDate;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate) && Objects.equals(insStartDate, event.insStartDate) && Objects.equals(insEndDate, event.insEndDate) && Objects.equals(teams, event.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, insStartDate, insEndDate, teams);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", insStartDate=" + insStartDate +
                ", insEndDate=" + insEndDate +
                ", teams=" + teams +
                '}';
    }
}
