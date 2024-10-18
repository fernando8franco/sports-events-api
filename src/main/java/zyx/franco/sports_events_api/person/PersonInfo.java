package zyx.franco.sports_events_api.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 10, unique = true)
    private String accountNumber;

    @Column(nullable = false, length = 75)
    private String firstName;

    @Column(nullable = false, length = 75)
    private String last_name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone_number;

    @Column(nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnTransformer(write = "?::type_gender")
    private Gender gender;

    @Column(nullable = false, length = 150, unique = true)
    private String photo;

    @Column(nullable = false)
    private Boolean isActive;
}
