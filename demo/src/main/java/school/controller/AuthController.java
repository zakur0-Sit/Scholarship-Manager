package school.controller;

import school.model.auth.User;
import school.payload.request.LoginRequest;
import school.payload.response.JwtResponse;
import school.payload.response.MessageResponse;
import school.repository.UserRepository;
import school.service.JwtService;
import school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> realUser = userRepository.findByUsername(loginRequest.getUsername());
        return realUser
                .map(user -> getJwtResponseBy(user.getUsername(), loginRequest.getPassword()))
                .orElseGet(() -> getJwtResponseBy(null, loginRequest.getPassword()));
    }



    private ResponseEntity getJwtResponseBy(String username, String password) {
        JwtResponse jwtResponse = jwtService.getJwtByCredentials(username, password);
        return ResponseEntity.ok(jwtResponse);
    }

}
