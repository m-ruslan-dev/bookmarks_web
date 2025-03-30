package bookmarks.DTO;

import bookmarks.interfaces.IWebpageMetadataInterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BookmarkPrefillDTO implements IWebpageMetadataInterface {
    private String title;
    private String iconUrl;
    private String description;
    private String category;
}
