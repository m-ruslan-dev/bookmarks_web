package bookmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import bookmarks.models.Bookmark;
import bookmarks.services.AuthenticationStatusService;
import bookmarks.services.BookmarkService;
import bookmarks.services.CurrentUserService;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    AuthenticationStatusService authenticationStatusService;

    @GetMapping("/get")
    public ResponseEntity<List<Bookmark>> getBookmarksForUser() {
        if (authenticationStatusService.isUserLoggedIn()) {
            Long userId = currentUserService.getUserId();
            List<Bookmark> bookmarks = bookmarkService.getBookmarksForUser(userId);
            return ResponseEntity.ok(bookmarks);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
