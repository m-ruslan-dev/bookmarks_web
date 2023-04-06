package bookmarks.interfaces;

import bookmarks.exceptions.UserIsNotLoggedInException;

public interface ILoggedUserService {
    public Long getUserId() throws UserIsNotLoggedInException;
}
