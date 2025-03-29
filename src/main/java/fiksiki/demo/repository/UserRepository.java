package fiksiki.demo.repository;

import fiksiki.demo.model.AuthUser;
import fiksiki.demo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <UserDetails, Long> {
    UserDetails getUserDetailsByAuthUser(AuthUser authUser);
    UserDetails getUserDetailsById(Long id);
}
