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
import bookmarks.exceptions.UserNotFoundException;
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
import bookmarks.services.LoggedUserService;
import bookmarks.services.UserService;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    UserService userService;
    @Autowired
    LoggedUserService loggedUserService;
    @Autowired
    AuthenticationStatusService authenticationStatusService;
    @Autowired
    bookmarkBuilder bookmarkBuilder;

    @GetMapping()
    public ResponseEntity<List<Bookmark>> getBookmarksForUser() {
        try {
            // If user is logged in - proceed and retrieve bookmarks
            Long userId = loggedUserService.getUserId();
            List<Bookmark> bookmarks = bookmarkService.getBookmarksForUser(userId);
            return ResponseEntity.ok(bookmarks);
        } catch (UserIsNotLoggedInException e) {
            // If user is not logged in, the exception is thrown and an unauthorized status
            // returned
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createBookmark(@RequestBody BookmarkInputDTO bookmarkInputDTO) {
        try {
            // Get logged user entity
            Long loggedUserId = loggedUserService.getUserId();
            User user = userService.getUserById(loggedUserId);

            // Get new bookmark entity object from builder
            String link = bookmarkInputDTO.getLink();
            String collection = bookmarkInputDTO.getCollection();
            Bookmark bookmark = bookmarkBuilder.buildBookmark(user, link, collection);

            // Try to save the bookmark
            bookmarkService.saveBookmark(bookmark);
            return ResponseEntity.ok().build();
        } catch (UserIsNotLoggedInException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (UserNotFoundException e) {
            // If the user ID has been retrieved, the user is considered logged in. An
            // invalid ID suggests that the issue lies within the code itself and that the
            // ID may have been lost or corrupted on its way to the controller
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An issue with the code has occurred while retrieving user information.");
        } catch (EmptyFieldsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookmark(@RequestBody BookmarkId bookmarkId) throws Exception {
        Long id = bookmarkId.getId();
        try {
            // Check if bookmark with such id exists, delete the bookmark
            bookmarkService.doesBookmarkByIdExist(id); // throws BookmarkNotFoundException
            bookmarkService.deleteBookmarkById(id);
            return ResponseEntity.ok().build();
        } catch (BookmarkNotFoundException e) {
            // If bookmark does not exist, return 404 error
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bookmark not found with id: " + id);
        }
    }
}
