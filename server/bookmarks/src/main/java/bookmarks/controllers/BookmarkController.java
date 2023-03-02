package bookmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import bookmarks.models.Bookmark;
import bookmarks.services.BookmarkService;

@RestController
@RequestMapping("bookmarks")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;

    @GetMapping("get/{user_id}")
    public List<Bookmark> getBookmarksForUser(@PathVariable("user_id") Long userId) {
        return bookmarkService.getBookmarksForUser(userId);
    }
}
