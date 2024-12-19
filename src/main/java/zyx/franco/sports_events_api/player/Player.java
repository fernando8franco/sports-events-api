package zyx.franco.sports_events_api.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import zyx.franco.sports_events_api.person.Gender;
import zyx.franco.sports_events_api.person.PersonInfo;
import zyx.franco.sports_events_api.team.Team;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

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

    public Player() {
    }

    public Player(UUID id, String accountNumber, String firstName, String last_name, String email, String phone_number, LocalDate birthday, Gender gender, String photo, Boolean isActive, Short semester, Short group, Boolean isCaptain, Team team) {
        super(id, accountNumber, firstName, last_name, email, phone_number, birthday, gender, photo, isActive);
        this.semester = semester;
        this.group = group;
        this.isCaptain = isCaptain;
        this.team = team;
    }

    public Short getSemester() {
        return semester;
    }

    public void setSemester(Short semester) {
        this.semester = semester;
    }

    public Short getGroup() {
        return group;
    }

    public void setGroup(Short group) {
        this.group = group;
    }

    public Boolean getCaptain() {
        return isCaptain;
    }

    public void setCaptain(Boolean captain) {
        isCaptain = captain;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Objects.equals(semester, player.semester) && Objects.equals(group, player.group) && Objects.equals(isCaptain, player.isCaptain) && Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), semester, group, isCaptain, team);
    }

    @Override
    public String toString() {
        return "Player{" +
                "semester=" + semester +
                ", group=" + group +
                ", isCaptain=" + isCaptain +
                ", team=" + team +
                '}';
    }
}
