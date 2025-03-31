package bookmarks.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class LLMRequestDTO {
    private String model;
    private List<LLMMessagesDTO> messages;
}
