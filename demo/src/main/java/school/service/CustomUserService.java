package school.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import school.model.auth.Role;
import school.model.auth.User;
import school.model.business.Student;
import school.repository.AuthenticationResponse;
import school.repository.StudentRepository;
import school.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
public class CustomUserService {


    private String emailUserSession = null;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private StudentRepository studentRepository;
    private AuthenticationManager authenticationManager;

    public CustomUserService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user = repository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
/*
    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        System.out.println("Authenticated user: " + request.getUsername());
        User user = repository.findByEmail(request.getEmail());
        System.out.println("Email: " + user.getEmail());
        String token = jwtService.generateToken(user);
        System.out.println("Token: " + token);
        return new AuthenticationResponse(token);
    }
*/
    public String authenticate(String username, String password) {
        System.out.println("CustomUserService.Authenticating user " + username);
        User user = repository.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            System.out.println("E coreeeect " + username);
            return jwtService.generateToken(user);
        }
        System.out.println("password 1 " +password);
        System.out.println("password 2 " +user.getPassword());
        System.out.println("response " + password.equals(user.getPassword()));
        System.out.println("A esuattttt");
        return null;
    }
    public void checkUser(User user) throws RuntimeException {
        System.out.println("Checking user");
        User existingUser = repository.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getId() != user.getId()) {
            System.out.println("Checking user already exists");
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        existingUser = repository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getId() != user.getId()) {
            System.out.println("test-------------------");
            throw new RuntimeException("User with username " + user.getUsername() + " already exists");
        }
    }
    public StudentService getUserDetailsByUsername(String username) {
        Student student = studentRepository.findByEmail(username);
        StudentService studentService = new StudentService();
        studentService.getStudentByEmail(username);
        return studentService;

    }
    public User save(User user) throws RuntimeException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        User existingUser = repository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        existingUser = repository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("User with username " + user.getUsername() + " already exists");
        }
        return repository.save(user);
    }

    public User updateUser(User oldUser, User newUser, String oldPassword) throws RuntimeException {
        User existingUser = repository.findByEmail(oldUser.getEmail());
        System.out.println();
        if (passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
            if (!(newUser.getUsername().isEmpty())) {
                existingUser.setUsername(newUser.getUsername());
            }
            if (!(newUser.getEmail().isEmpty())) {
                existingUser.setEmail(newUser.getEmail());
            }
            if (!(newUser.getPassword().isEmpty())) {
                existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }
        }
        else {
            throw new RuntimeException("Current password incorrect");
        }
        System.out.println(oldUser);
        System.out.println(existingUser);
        System.out.println(oldPassword);
        checkUser(existingUser);
        return repository.save(existingUser);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Long findUserIdByUsername(String username) {
         return repository.findByUsername(username).getId();
    }
}