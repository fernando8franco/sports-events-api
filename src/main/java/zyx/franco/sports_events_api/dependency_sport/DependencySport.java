package zyx.franco.sports_events_api.dependency_sport;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.sport.Sport;
import zyx.franco.sports_events_api.team.Team;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonBackReference
    private Dependency dependency;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    @JsonBackReference
    private Sport sport;

    @OneToMany(mappedBy = "dependencySport")
    @JsonManagedReference
    private List<Team> teams;
}
