package fiksiki.demo.repository;

import fiksiki.demo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserDetails, Long> {

}
