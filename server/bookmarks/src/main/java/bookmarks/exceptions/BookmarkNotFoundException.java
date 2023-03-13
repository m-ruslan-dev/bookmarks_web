package bookmarks.exceptions;

public class BookmarkNotFoundException extends Exception {
    public BookmarkNotFoundException(String message) {
        super(message);
    }
}