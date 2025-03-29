package fiksiki.demo.services;

import fiksiki.demo.model.AuthUser;
import fiksiki.demo.model.UserDetails;
import fiksiki.demo.repository.AuthUserRepository;
import fiksiki.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthUserRepository authUserRepo) {
        this.userRepository = userRepository;
        this.authUserRepository = authUserRepo;
    }

    public UserDetails get(final Long id) {
        UserDetails user = userRepository.getUserDetailsById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public List<UserDetails> findAll() {
        return userRepository.findAll();
    }

    public UserDetails update(final Long id, UserDetails updatedUser) {
        AuthUser authUser = authUserRepository.findById(id);
        UserDetails user = userRepository.getUserDetailsByAuthUser(authUser);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setNationality(updatedUser.getNationality());
        user.setSkins(updatedUser.getSkins());
        user.setPoints(updatedUser.getPoints());
        user.setMoney(updatedUser.getMoney());
        user.setDifficulty(updatedUser.getDifficulty());
        user.setDob(updatedUser.getDob());
        user.setLanguage(updatedUser.getLanguage());
        userRepository.save(user);
        return user;
    }
}
