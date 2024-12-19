package zyx.franco.sports_events_api.dependency_sport;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.sport.Sport;
import zyx.franco.sports_events_api.team.Team;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_dependencies_sports")
public class DependencySport {
    @Id
    @SequenceGenerator(
            name = "dependency_sport_seq",
            sequenceName = "t_dependencies_sports_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dependency_sport_seq"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dependency_id")
    @JsonManagedReference
    private Dependency dependency;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    @JsonManagedReference
    private Sport sport;

    @OneToMany(mappedBy = "dependencySport")
    @JsonBackReference
    private List<Team> teams;

    public DependencySport() {
    }

    public DependencySport(Integer id, Dependency dependency, Sport sport, List<Team> teams) {
        this.id = id;
        this.dependency = dependency;
        this.sport = sport;
        this.teams = teams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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
        DependencySport that = (DependencySport) o;
        return Objects.equals(id, that.id) && Objects.equals(dependency, that.dependency) && Objects.equals(sport, that.sport) && Objects.equals(teams, that.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dependency, sport, teams);
    }

    @Override
    public String toString() {
        return "DependencySport{" +
                "id=" + id +
                ", dependency=" + dependency +
                ", sport=" + sport +
                ", teams=" + teams +
                '}';
    }
}
