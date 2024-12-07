package zyx.franco.sports_events_api.dependency;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.employee.Employee;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_dependency")
public class Dependency {
    @Id
    @SequenceGenerator(
            name = "dependency_seq",
            sequenceName = "t_dependency_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dependency_seq"
    )
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnTransformer(write = "?::dependency_category")
    private DependencyCategory category;

    @ManyToMany(mappedBy = "dependency")
    @JsonManagedReference
    private List<DependencySport> dependenciesSports;

    @OneToMany(mappedBy = "dependency")
    @JsonManagedReference
    private List<Employee> employees;
}
