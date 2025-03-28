package fiksiki.demo.controller;

import fiksiki.demo.model.UserDetails;
import fiksiki.demo.services.JWTService;
import fiksiki.demo.services.UserService;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
    private final UserService userService;
    private final JWTService jwtService;

    @Autowired
    public UserDetailsController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping(path="/test")
    public ResponseEntity<String> test(@RequestHeader("Authorization") String auth) {
        String token = null;
        String username = null;

        if (auth != null && auth.startsWith("Bearer ")) {
            token = auth.substring(7);
            username = jwtService.extractUserName(token);
            return new ResponseEntity<>(username, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("Invalid token", HttpStatusCode.valueOf(401));
    }
}
