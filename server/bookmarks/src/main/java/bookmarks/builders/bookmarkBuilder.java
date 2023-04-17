package bookmarks.builders;

import org.springframework.stereotype.Component;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.interfaces.IBookmarkBuilder;

import bookmarks.models.Bookmark;
import bookmarks.models.User;

@Component
public class BookmarkBuilder implements IBookmarkBuilder {
    Bookmark bookmark = new Bookmark();

    public Bookmark buildBookmark(User user, String link, String collection) throws EmptyFieldsException {
        if (user == null || link == null || collection == null) {
            throw new EmptyFieldsException("Cannot create bookmark with empty fields.");
        } else {
            bookmark.setUser(user);
            bookmark.setLink(link);
            bookmark.setCollection(collection);
            return bookmark;
        }
    }
}
