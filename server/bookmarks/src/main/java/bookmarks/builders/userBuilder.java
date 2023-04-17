package bookmarks.builders;

import org.springframework.stereotype.Component;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.interfaces.IUserBuilder;
import bookmarks.models.User;

@Component
public class userBuilder implements IUserBuilder {
    User user = new User();

    public User buildUser(String username, String password, String email) throws EmptyFieldsException {
        if (username == null || password == null || email == null) {
            throw new EmptyFieldsException("Cannot create a user with empty fields");
        } else {
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            return user;
        }
    }
}