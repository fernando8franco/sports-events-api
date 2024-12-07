package zyx.franco.sports_events_api.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import zyx.franco.sports_events_api.dependency.Dependency;
import zyx.franco.sports_events_api.person.Gender;
import zyx.franco.sports_events_api.person.PersonInfo;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "t_employee")
public class Employee extends PersonInfo {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnTransformer(write = "?::type_role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "dependency_id")
    @JsonBackReference
    private Dependency dependency;

    public Employee() {
    }

    public Employee(UUID id, String accountNumber, String firstName, String last_name, String email, String phone_number, LocalDate birthday, Gender gender, String photo, Boolean isActive, Role role, Dependency dependency) {
        super(id, accountNumber, firstName, last_name, email, phone_number, birthday, gender, photo, isActive);
        this.role = role;
        this.dependency = dependency;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return role == employee.role && Objects.equals(dependency, employee.dependency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, dependency);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "role=" + role +
                ", dependency=" + dependency +
                '}';
    }
}
