package zyx.franco.sports_events_api.dependency;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_dependency")
public class Dependency {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 150)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DependencyCategory type;
}
