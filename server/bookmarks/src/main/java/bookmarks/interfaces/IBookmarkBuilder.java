package bookmarks.interfaces;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.mysql.models.Bookmark;
import bookmarks.mysql.models.User;

public interface IBookmarkBuilder {
    public Bookmark buildBookmark(User user, String title, String description, String link, String collection) throws EmptyFieldsException;
}
