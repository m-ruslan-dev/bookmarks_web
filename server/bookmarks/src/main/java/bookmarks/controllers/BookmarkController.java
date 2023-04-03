package bookmarks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import bookmarks.exceptions.UserIsNotLoggedInException;
import bookmarks.DTO.BookmarkId;
import bookmarks.DTO.BookmarkInputDTO;
import bookmarks.builders.bookmarkBuilder;
import bookmarks.exceptions.BookmarkNotFoundException;
import bookmarks.exceptions.EmptyFieldsException;

import org.springframework.web.server.ResponseStatusException;

import bookmarks.models.Bookmark;
import bookmarks.models.User;
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
    @Autowired
    bookmarkBuilder bookmarkBuilder;

    @GetMapping()
    public ResponseEntity<List<Bookmark>> getBookmarksForUser() {
        try {
            // If user is logged in - proceed and retrieve bookmarks
            if (authenticationStatusService.isUserLoggedIn()) {
                Long userId = currentUserService.getUserId();
                List<Bookmark> bookmarks = bookmarkService.getBookmarksForUser(userId);
                return ResponseEntity.ok(bookmarks);
            }
        } catch (UserIsNotLoggedInException e) {
            // If user is not logged in, the exception is thrown and an unauthorized status
            // returned
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // If user is logged in, but an unexpected error occured and bookmarks were not
        // returned
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping
    public ResponseEntity<?> createBookmark(@RequestBody BookmarkInputDTO bookmarkInputDTO) {
        // Get user entity
        User user = currentUserService.getUser();
        bookmarkBuilder.buildUser(user);
        bookmarkBuilder.buildLink(bookmarkInputDTO.getLink());
        bookmarkBuilder.buildCollection(bookmarkInputDTO.getCollection());
        try {
            Bookmark bookmark = bookmarkBuilder.getBookmark();
            bookmarkService.saveBookmark(bookmark);
            return ResponseEntity.ok().build();
        } catch (EmptyFieldsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookmark(@RequestBody BookmarkId bookmarkId) throws Exception {
        Long id = bookmarkId.getId();
        try {
            // Check if user with such id exists, delete the bookmark
            bookmarkService.doesBookmarkByIdExist(id); // throws BookmarkNotFoundException
            bookmarkService.deleteBookmarkById(id);
            return ResponseEntity.ok().build();
        } catch (BookmarkNotFoundException e) {
            // If bookmark does not exist, return 404 error
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bookmark not found with id: " + id);
        }
    }
}
