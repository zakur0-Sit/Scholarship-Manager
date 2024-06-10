package school.security.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import school.model.auth.User;
import school.model.auth.UserDetail;
import school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Loading user details for user: " + email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = userRepository.findByUsername(email);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
        }
        System.out.println("CustomUserDetailsService: User found: " + user.getUsername());
        return new UserDetail(user);
    }
}


