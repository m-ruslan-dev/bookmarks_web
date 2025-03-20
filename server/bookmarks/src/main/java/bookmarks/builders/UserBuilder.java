package bookmarks.builders;

import org.springframework.stereotype.Component;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.interfaces.IUserBuilder;
import bookmarks.models.Role;
import bookmarks.models.User;

@Component
public class UserBuilder implements IUserBuilder {

    public User buildUser(String username, String password, String email, Role role) throws EmptyFieldsException {
        if (username == null || password == null || email == null || role == null) {
            throw new EmptyFieldsException("Cannot create a user with empty fields");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.getRoles().add(role);
            return user;
        }
    }
}