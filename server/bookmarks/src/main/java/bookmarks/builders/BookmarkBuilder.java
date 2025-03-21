package bookmarks.builders;

import org.springframework.stereotype.Component;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.interfaces.IBookmarkBuilder;

import bookmarks.models.Bookmark;
import bookmarks.models.User;

@Component
public class BookmarkBuilder implements IBookmarkBuilder {

    public Bookmark buildBookmark(User user, String title, String description, String link, String collection) throws EmptyFieldsException {
        if (user == null || description == null || link == null || collection == null) {
            throw new EmptyFieldsException("Cannot create bookmark with empty fields.");
        } else {
            Bookmark bookmark = new Bookmark();
            bookmark.setUser(user);
            bookmark.setTitle(title);
            bookmark.setDescription(description);
            bookmark.setLink(link);
            bookmark.setCollection(collection);
            return bookmark;
        }
    }
}
