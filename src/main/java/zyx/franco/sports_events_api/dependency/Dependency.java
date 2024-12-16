package zyx.franco.sports_events_api.dependency;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import zyx.franco.sports_events_api.dependency_sport.DependencySport;
import zyx.franco.sports_events_api.employee.Employee;

import java.util.List;
import java.util.Objects;

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

    public Dependency() {
    }

    public Dependency(Integer id, String name, DependencyCategory category, List<DependencySport> dependenciesSports, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.dependenciesSports = dependenciesSports;
        this.employees = employees;
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

    public DependencyCategory getCategory() {
        return category;
    }

    public void setCategory(DependencyCategory category) {
        this.category = category;
    }

    public List<DependencySport> getDependenciesSports() {
        return dependenciesSports;
    }

    public void setDependenciesSports(List<DependencySport> dependenciesSports) {
        this.dependenciesSports = dependenciesSports;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependency that = (Dependency) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && category == that.category && Objects.equals(dependenciesSports, that.dependenciesSports) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, dependenciesSports, employees);
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", dependenciesSports=" + dependenciesSports +
                ", employees=" + employees +
                '}';
    }
}
