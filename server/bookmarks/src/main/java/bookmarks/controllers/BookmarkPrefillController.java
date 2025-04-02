package bookmarks.controllers;

import bookmarks.DTO.BookmarkPrefillDTO;
import bookmarks.DTO.BookmarkPrefillRequestDTO;
import bookmarks.services.BookmarkPrefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark-prefill")
public class BookmarkPrefillController {
    @Autowired
    BookmarkPrefillService bookmarkPrefillService;

    @PostMapping
    public ResponseEntity<?> getBookmarkPrefill(@RequestBody BookmarkPrefillRequestDTO bookmarkPrefillRequestDTO) {
        String url = bookmarkPrefillRequestDTO.getUrl();
        BookmarkPrefillDTO bookmarkPrefillDTO = bookmarkPrefillService.getBookmarkPrefill(url);
        return ResponseEntity.ok(bookmarkPrefillDTO);
    }
}
