package zyx.franco.sports_events_api.sport;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_sport")
public class Sport {
    @Id
    @SequenceGenerator(
            name = "sport_seq",
            sequenceName = "t_sport_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sport_seq"
    )
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnTransformer(write = "?::sport_category")
    private SportCategory category;

    @Column(nullable = false)
    private Integer numPlayers;

    @Column(nullable = false)
    private Integer numExtraPlayers;

    @Column(nullable = false)
    private Boolean hasCaptain;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToMany(mappedBy = "sport")
    @JsonManagedReference
    private List<DependencySport> dependenciesSports;

    public Sport() {
    }

    public Sport(Integer id, String name, SportCategory category, Integer numPlayers, Integer numExtraPlayers, Boolean hasCaptain, Boolean isActive, List<DependencySport> dependenciesSports) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.numPlayers = numPlayers;
        this.numExtraPlayers = numExtraPlayers;
        this.hasCaptain = hasCaptain;
        this.isActive = isActive;
        this.dependenciesSports = dependenciesSports;
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

    public SportCategory getCategory() {
        return category;
    }

    public void setCategory(SportCategory category) {
        this.category = category;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Integer getNumExtraPlayers() {
        return numExtraPlayers;
    }

    public void setNumExtraPlayers(Integer numExtraPlayers) {
        this.numExtraPlayers = numExtraPlayers;
    }

    public Boolean getHasCaptain() {
        return hasCaptain;
    }

    public void setHasCaptain(Boolean hasCaptain) {
        this.hasCaptain = hasCaptain;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<DependencySport> getDependenciesSports() {
        return dependenciesSports;
    }

    public void setDependenciesSports(List<DependencySport> dependenciesSports) {
        this.dependenciesSports = dependenciesSports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return Objects.equals(id, sport.id) && Objects.equals(name, sport.name) && category == sport.category && Objects.equals(numPlayers, sport.numPlayers) && Objects.equals(numExtraPlayers, sport.numExtraPlayers) && Objects.equals(hasCaptain, sport.hasCaptain) && Objects.equals(isActive, sport.isActive) && Objects.equals(dependenciesSports, sport.dependenciesSports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, numPlayers, numExtraPlayers, hasCaptain, isActive, dependenciesSports);
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", numPlayers=" + numPlayers +
                ", numExtraPlayers=" + numExtraPlayers +
                ", hasCaptain=" + hasCaptain +
                ", isActive=" + isActive +
                ", dependenciesSports=" + dependenciesSports +
                '}';
    }
}
