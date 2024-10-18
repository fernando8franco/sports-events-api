package zyx.franco.sports_events_api.sport;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @ColumnTransformer(write = "?::type_gender")
    private Gender gender;

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
}
