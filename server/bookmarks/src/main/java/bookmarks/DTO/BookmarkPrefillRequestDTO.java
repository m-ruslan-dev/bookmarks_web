package bookmarks.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkPrefillRequestDTO {
    @NotNull
    String url;
}
