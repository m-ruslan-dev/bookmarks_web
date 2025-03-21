package bookmarks.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkInputDTO {
    private String title;
    private String description;
    private String link;
    private String collection;
}
