package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.model.auth.User;
import school.payload.request.LoginRequest;
import school.payload.response.AuthResponse;
import school.repository.AuthenticationResponse;
import school.repository.UserRepository;
import school.service.CustomUserService;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
public class AuthController {

    public static String emailUserSession = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private UserRepository userRepository;


    /*
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request, HttpSession session) {
        System.out.println("AICIIIIIIIIIIIIII");
        AuthenticationResponse response = userService.authenticate(request);
        if (response != null && response.getToken() != null) {
            System.out.println("BA AICIIIIIIIIIIIIIII");
            session.setAttribute("user", request);
            emailUserSession = request.getEmail();
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }*/
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println("token: " + token);
        System.out.println("email: " + loginRequest.getUsername());
        System.out.println("password: " + loginRequest.getPassword());
        if (token != null) {
            System.out.println("token: " + token);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user, HttpSession session) {
        try {
            System.out.println("test--------------------------------------------");
            userService.checkUser(user);
            System.out.println("checked--------------------------------------------");
            session.setAttribute("user", user);
            System.out.println(user);
            return ResponseEntity.ok().body(new AuthenticationResponse("Redirect to /validation"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    /*
    @ResponseBody
    @GetMapping("/index")
    public ResponseEntity<?> getUserDetails(@RequestBody Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }*/
/*
    public Long findStudentIdByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        return user.getId();
    }*/
}
