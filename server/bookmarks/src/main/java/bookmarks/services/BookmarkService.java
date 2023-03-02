package bookmarks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookmarks.repositories.BookmarkRepository;
import bookmarks.models.Bookmark;

import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<Bookmark> getBookmarksForUser(Long userId) {
        return bookmarkRepository.findByUser_Id(userId);
    }
}
