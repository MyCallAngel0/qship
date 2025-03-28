package fiksiki.demo.services;

import fiksiki.demo.model.AuthUser;
import fiksiki.demo.repository.AuthUserRepository;
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


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AuthUser register(AuthUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public ResponseEntity<String> verify(AuthUser user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        System.out.println("Here");
        if (authentication.isAuthenticated()) {
            System.out.println("There");
            return new ResponseEntity<>("{ \"jwt\": \"" + jwtService.generateToken(user.getUsername()) + "\" }", HttpStatusCode.valueOf(200));
        } else {
            System.out.println("Everywhere");
            return new ResponseEntity<>("Authorization failed", HttpStatusCode.valueOf(401));
        }
    }

}
