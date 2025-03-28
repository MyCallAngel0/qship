package fiksiki.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@Entity
@Table
public class UserDetails {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name="date_of_birth")
    private LocalDate dob;

    @Min(value = 1)
    @Max(value = 100)
    private Integer difficulty;

    private Integer money;

    private Long points;

    private String skins;

    @Enumerated
    @Column
    private Nationality nationality;

    @Enumerated
    @Column
    private Language language;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="auth_user_id")
    private AuthUser auth_user;

    public enum Nationality {
        ROMANIAN,
        RUSSIAN,
        BRITISH,
        AMERICAN,
        GERMAN,
        FRENCH,
        SPANISH,
        CHINA,
        JAPAN,
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
