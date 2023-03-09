package bookmarks.interfaces;

import bookmarks.exceptions.UserIsNotLoggedInException;

public interface IAuthenticationStatusService {
    public Boolean isUserLoggedIn() throws UserIsNotLoggedInException;
}
