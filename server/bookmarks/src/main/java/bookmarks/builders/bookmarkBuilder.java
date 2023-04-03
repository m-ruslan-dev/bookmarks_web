package bookmarks.builders;

import org.springframework.stereotype.Component;

import bookmarks.exceptions.EmptyFieldsException;
import bookmarks.interfaces.IBookmarkBuilder;

import bookmarks.models.Bookmark;
import bookmarks.models.User;

@Component
public class bookmarkBuilder implements IBookmarkBuilder {

    Bookmark bookmark = new Bookmark();

    public void buildLink(String link) {
        bookmark.setLink(link);
    }

    public void buildCollection(String collection) {
        bookmark.setCollection(collection);
    }

    public void buildUser(User user) {
        bookmark.setUser(user);
    }

    public Bookmark getBookmark() throws EmptyFieldsException {
        if (bookmark.getLink() == null || bookmark.getCollection() == null || bookmark.getUser() == null) {
            throw new EmptyFieldsException("Cannot create bookmark with empty fields");
        } else {
            return bookmark;
        }
    }
}
