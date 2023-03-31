package bookmarks.interfaces;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.models.Bookmark;
import bookmarks.models.User;

public interface IBookmarkBuilder {
    public void buildLink(String link);

    public void buildCollection(String collection);

    public void buildUser(User user);

    public Bookmark getBookmark() throws EmptyFieldsException;
}
