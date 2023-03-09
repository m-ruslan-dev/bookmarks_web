package bookmarks.exceptions;

public class UserIsNotLoggedInException extends Exception {
    public UserIsNotLoggedInException(String message) {
        super(message);
    }
}
