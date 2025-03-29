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

import java.util.*;

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

    @GetMapping(path = "/all")
    public ResponseEntity<List<Map<String, ?>>> getAllUsers() {
        List<UserDetails> users = userService.findAll();
        List<Map<String, ?>> responseList = new ArrayList<>();
        users.forEach(user -> {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("username", user.getAuthUser().getUsername());
            responseMap.put("points", user.getPoints());
            responseMap.put("money", user.getMoney());
            responseMap.put("nationality", user.getNationality().toString());
            responseMap.put("language", user.getLanguage().toString());
            responseMap.put("dob", user.getDob());
            responseMap.put("difficulty", user.getDifficulty());
            responseList.add(responseMap);
        });
        return new ResponseEntity<>(responseList, HttpStatusCode.valueOf(200));
    }

    @GetMapping(path="/{user_id}")
    public ResponseEntity<Map<String, ?>> getUserDetails(@RequestHeader("Authorization") String auth,
                                                         @PathVariable(name="user_id") Long id) {
        String token = null;
        String username = null;

        if (auth != null && auth.startsWith("Bearer ")) {
            token = auth.substring(7);
            username = jwtService.extractUserName(token);
            try {
                UserDetails user = userService.get(id);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("username", user.getAuthUser().getUsername());
                responseMap.put("points", user.getPoints());
                responseMap.put("money", user.getMoney());
                responseMap.put("nationality", user.getNationality().toString());
                responseMap.put("language", user.getLanguage().toString());
                responseMap.put("dob", user.getDob());
                responseMap.put("difficulty", user.getDifficulty());
                return new ResponseEntity<>(responseMap, HttpStatusCode.valueOf(200));
            }
            catch (Exception e) {
                return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatusCode.valueOf(400));
            }
        }
        return new ResponseEntity<>(Map.of("error", "Invalid token"), HttpStatusCode.valueOf(401));
    }

    @PutMapping(path="/{user_id}")
    public ResponseEntity<Map<String, ?>> updateUserDetails(@RequestHeader("Authorization") String auth,
                                                            @PathVariable(name="user_id") Long id,
                                                            @RequestBody UserDetails userDetails) {
        String token = null;
        String username = null;

        if (auth != null && auth.startsWith("Bearer ")) {
            token = auth.substring(7);
            try {
                UserDetails user = userService.update(id, userDetails);
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("username", user.getAuthUser().getUsername());
                responseMap.put("points", user.getPoints());
                responseMap.put("money", user.getMoney());
                responseMap.put("nationality", user.getNationality().toString());
                responseMap.put("language", user.getLanguage().toString());
                responseMap.put("dob", user.getDob());
                responseMap.put("difficulty", user.getDifficulty());
                return new ResponseEntity<>(responseMap, HttpStatusCode.valueOf(200));
            }
            catch (Exception e) {
                return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatusCode.valueOf(400));
            }
        }
        return new ResponseEntity<>(Map.of("error", "Invalid token"), HttpStatusCode.valueOf(401));
    }
}
