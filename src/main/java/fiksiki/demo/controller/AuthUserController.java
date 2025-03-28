package fiksiki.demo.controller;

import fiksiki.demo.model.AuthUser;
import fiksiki.demo.services.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/")
public class AuthUserController {

    @Autowired
    private AuthUserService service;


    @PostMapping("/register")
    public ResponseEntity<AuthUser> register(@RequestBody AuthUser user) {
        AuthUser registeredUser = service.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatusCode.valueOf(200));

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthUser user) {
        return service.verify(user);
    }
}