package bookmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import bookmarks.exceptions.UserIsNotLoggedInException;
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
        try {
            // If user is logged in - proceed and retrieve bookmarks
            if (authenticationStatusService.isUserLoggedIn()) {
                Long userId = currentUserService.getUserId();
                List<Bookmark> bookmarks = bookmarkService.getBookmarksForUser(userId);
                return ResponseEntity.ok(bookmarks);
            }
        } catch (UserIsNotLoggedInException e) {
            // If user is not logged in, the exception is thrown and handled
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // If user is logged in, but an unexpected error occured and bookmarks were not
        // returned
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
