package bookmarks.interfaces;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.models.Bookmark;
import bookmarks.models.User;

public interface IBookmarkBuilder {
    public Bookmark buildBookmark(User user, String title, String link, String collection) throws EmptyFieldsException;
}
