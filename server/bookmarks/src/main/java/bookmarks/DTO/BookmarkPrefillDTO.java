package bookmarks.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BookmarkPrefillDTO {
    private String title;
    private String faviconURL;
    private String description;
    private String category;
}
