package school.service;

import school.model.auth.ERole;
import school.model.auth.Role;
import school.model.auth.User;
import school.model.auth.UserDetails;
import school.repository.RoleRepository;
import school.repository.UserDetailsRepository;
import school.repository.UserRepository;
import school.security.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService {

    public static final String DEFAULT_USER = "default@caziere.ro";
    public static final String DEFAULT_PASS = "default";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;


    public ERole getCurrentUserRole() {
        Set<ERole> userRoles = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getAuthorities()
                .stream()
                .map(authority -> ERole.valueOf(authority.getAuthority()))
                .collect(Collectors.toSet());

        return ERole.getGreatestERole(userRoles);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByRole(Role role) {
        return userRepository.findByRolesContains(role);
    }

    public List<User> findAllByRole(ERole role) {
        return findAllByRole(getRoleBy(role));
    }

    public Role getRoleBy(ERole role) {
        return roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public User find(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean exist(String username) {
        return userRepository.existsByUsername(username);
    }

    public User create(String username, String password, ERole role) {
        Set<String> rolesAsString = new HashSet<>(List.of(role == null ? ERole.ROLE_USER.getAlias() : role.getAlias()));
        return create(username, password, rolesAsString);
    }

    public User create(String username, String password, Set<String> rolesAsStrings) {
        return create(username, password, rolesAsStrings, null, null);
    }

    public User create(String username, String password, Set<String> rolesAsStrings, String firstName, String lastName) {
        Set<Role> roles = getRolesByRolesSetAsString(rolesAsStrings);
        String encodedPassword = encoder.encode(password);
        UserDetails userDetails = null;
        if (firstName != null && lastName != null) {
            userDetails = new UserDetails(firstName, lastName);
            userDetails = userDetailsRepository.save(userDetails);
        }
        User user = new User(username, encodedPassword, userDetails);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User findOrCreate(String username, String password, ERole role) {
        User user = find(username);
        return user == null ? create(username, password, role) : user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserDetails save(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    private Set<Role> getRolesByRolesSetAsString(Set<String> rolesAsStrings) {
        if (rolesAsStrings == null) {
            rolesAsStrings = new HashSet<>(List.of(ERole.ROLE_USER.getAlias()));
        }
        return rolesAsStrings.stream()
                .map(this::getRoleOrCreate)
                .collect(Collectors.toSet());
    }

    private Role getRoleOrCreate(String role) {
        ERole enumByAlias = ERole.getEnumByAlias(role);
        Optional<Role> byName = roleRepository.findByName(enumByAlias);
        return byName.orElseGet(() -> roleRepository.save(new Role(enumByAlias)));
    }

}
