package bookmarks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookmarks.exceptions.UserNotFoundException;
import bookmarks.mysql.models.User;
import bookmarks.mysql.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("Could not find user with id: " + id));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
