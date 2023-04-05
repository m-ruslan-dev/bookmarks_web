package bookmarks.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bookmarks.repositories.UserRepository;
import bookmarks.security.CustomUserDetails;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findUserByUsername(username);

        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
    }
}
