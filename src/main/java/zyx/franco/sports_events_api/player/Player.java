package zyx.franco.sports_events_api.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zyx.franco.sports_events_api.person.PersonInfo;
import zyx.franco.sports_events_api.team.Team;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_player")
public class Player extends PersonInfo {
    @Column(nullable = false)
    private Short semester;

    @Column(nullable = false, name = "p_group")
    private Short group;

    @Column(nullable = false)
    private Boolean isCaptain;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
}
