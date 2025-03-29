package fiksiki.demo.repository;

import fiksiki.demo.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    AuthUser findByUsername(String username);
    AuthUser findById(Long id);
}
