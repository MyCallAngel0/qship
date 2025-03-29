package fiksiki.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name="user_info")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @Column(name="date_of_birth")
    private LocalDate dob;

    @Min(value = 1)
    @Max(value = 100)
    private Integer difficulty;

    @Min(value = 0)
    private Integer money;

    @Min(value = 0)
    private Long points;

    private String skins;

    @Enumerated
    @Column
    private Nationality nationality;

    @Enumerated
    @Column
    private Language language;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="auth_user", nullable = false)
    private AuthUser authUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getSkins() {
        return skins;
    }

    public void setSkins(String skins) {
        this.skins = skins;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public enum Nationality {
        ROMANIAN,
        RUSSIAN,
        BRITISH,
        AMERICAN,
        GERMAN,
        FRENCH,
        SPANISH,
        CHINA,
        MOLDOVAN,
        TURK,

    }

    public enum Language {
        ENGLISH,
        RUSSIAN,
        ROMANIAN,
        TURKISH,
        GERMAN,
        ITALIAN,
        FRENCH,
        SPANISH,
        CHINESE,
        JAPANESE
    }
}
