package example.com.taskmanagement.security.services;

import example.com.taskmanagement.models.User;
import example.com.taskmanagement.repositories.UserRepository;
import example.com.taskmanagement.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with "+ username + " does not exist.");
        }

        CustomUserDetails userDetails = new CustomUserDetails(optionalUser.get());

        return userDetails;
    }
}
