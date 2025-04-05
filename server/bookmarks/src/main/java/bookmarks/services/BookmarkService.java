package bookmarks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookmarks.mysql.repositories.BookmarkRepository;
import bookmarks.mysql.models.Bookmark;
import bookmarks.exceptions.BookmarkNotFoundException;

import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<Bookmark> getBookmarksForUser(Long userId) {
        return bookmarkRepository.findByUser_Id(userId);
    }

    public Boolean doesBookmarkByIdExist(Long id) throws Exception {
        if (bookmarkRepository.existsById(id)) {
            return true;
        } else {
            throw new BookmarkNotFoundException("Bookmark not found with id: " + id);
        }
    }

    public void deleteBookmarkById(Long id) {
        bookmarkRepository.deleteById(id);
    }

    public void saveBookmark(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }
}
