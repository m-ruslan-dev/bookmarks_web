package bookmarks.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LLMMessagesDTO {
    private String role;
    private String content;

    public LLMMessagesDTO(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
