package fiksiki.demo.services;

import fiksiki.demo.model.AuthUser;
import fiksiki.demo.model.UserDetails;
import fiksiki.demo.repository.AuthUserRepository;
import fiksiki.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private AuthUserRepository repo;

    @Autowired
    private UserRepository userRepository;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AuthUser register(AuthUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        UserDetails userDetails = new UserDetails();
        userDetails.setMoney(0);
        userDetails.setDifficulty(30);
        userDetails.setPoints(0L);
        userDetails.setNationality(UserDetails.Nationality.MOLDOVAN);
        userDetails.setLanguage(UserDetails.Language.ENGLISH);
        repo.save(user);
        userDetails.setAuthUser(repo.findByUsername(user.getUsername()));
        userRepository.save(userDetails);
        return user;
    }

    public ResponseEntity<String> verify(AuthUser user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>("{ \"jwt\": \"" + jwtService.generateToken(user.getUsername()) + "\" }", HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>("Authorization failed", HttpStatusCode.valueOf(401));
        }
    }

}
