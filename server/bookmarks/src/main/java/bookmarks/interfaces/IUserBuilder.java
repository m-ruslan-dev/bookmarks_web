package bookmarks.interfaces;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.mysql.models.Role;
import bookmarks.mysql.models.User;

public interface IUserBuilder {
    public User buildUser(String username, String password, String email, Role role) throws EmptyFieldsException;
}
