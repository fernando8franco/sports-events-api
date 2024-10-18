package zyx.franco.sports_events_api.dependency_sport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.sport.Sport;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_dependencies_sports")
public class DependencySport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "dependency_id")
    private Dependency dependency;
    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;
}
