package bookmarks.interfaces;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.models.User;

public interface IUserBuilder {
    public User buildUser(String username, String password, String email) throws EmptyFieldsException;
}
