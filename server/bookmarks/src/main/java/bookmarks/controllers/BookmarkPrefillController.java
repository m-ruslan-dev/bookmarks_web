package bookmarks.controllers;

import bookmarks.exceptions.UserIsNotLoggedInException;
import bookmarks.models.Bookmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("prefill-bookmark")
public class BookmarkPrefillController {

    @GetMapping()
    public String getPrefill() {
        return "Hello";
    }
}
