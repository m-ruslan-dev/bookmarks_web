package bookmarks.services;

import bookmarks.DTO.BookmarkPrefillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkPrefillService {
    @Autowired
    WebsiteMetadataService websiteMetadataService;
    @Autowired
    LLMService llmService;

    public BookmarkPrefillDTO getBookmarkPrefill(String url) {
        BookmarkPrefillDTO bookmarkPrefillDTO = new BookmarkPrefillDTO();
        // Populate the DTO with webpage title and icon
        bookmarkPrefillDTO = (BookmarkPrefillDTO) websiteMetadataService.getMetadata(url, bookmarkPrefillDTO);
        // Populate the DTO with description and category
        bookmarkPrefillDTO = (BookmarkPrefillDTO) llmService.generateBookmarkPrefill(url, bookmarkPrefillDTO);

        return bookmarkPrefillDTO;
    }
}
