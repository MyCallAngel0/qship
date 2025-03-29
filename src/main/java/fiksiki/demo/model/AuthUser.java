package fiksiki.demo.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="auth_user")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="auth_user_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
