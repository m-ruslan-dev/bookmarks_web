package bookmarks.services;

import bookmarks.DTO.BookmarkPrefillDTO;
import bookmarks.DTO.LLMMessagesDTO;
import bookmarks.DTO.LLMRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class LLMService {

    private String performApiCall(String url, LLMRequestDTO llmRequestDTO) {
        String API_key = "";
        WebClient client = WebClient
                .builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_key)
                .build();

        return client
                .post()
                .uri(url)
                .bodyValue(llmRequestDTO)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new RuntimeException("Failed to fetch metadata. Error: " + response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Failed to fetch metadata. Error: " + response.statusCode())))
                .bodyToMono(String.class)
                .block();
    };

    // Method is responsible for getting the bookmark description and category
    public BookmarkPrefillDTO generateBookmarkPrefill(BookmarkPrefillDTO bookmarkPrefillDTO, String url) {
        LLMRequestDTO llmRequestDTO = new LLMRequestDTO();

        // Construct a JSON object with LLM messages
        List<LLMMessagesDTO> messages = new ArrayList<>();
        // Add instructions message for generating prefill data
        messages.add(new LLMMessagesDTO("system", "Test"));
        // Provide relevant prompt to generate data
        messages.add(new LLMMessagesDTO("user", "Webpage URL: '" + url + "'. Webpage title: '" + bookmarkPrefillDTO.getTitle() + "'."))

        // Construct a JSON with model and messages
        llmRequestDTO.setModel("mistral-large-latest");
        llmRequestDTO.setMessages(messages);

        String LLMresponse = performApiCall("https://api.mistral.ai/v1/chat/completions", llmRequestDTO);

        String description = getBookmarkPrefillDescription(LLMresponse);
        String category = getBookmarkPrefillCategory(LLMresponse);

        bookmarkPrefillDTO.setDescription(description);
        bookmarkPrefillDTO.setCategory(category);

        return bookmarkPrefillDTO;
    }

    private String getBookmarkPrefillDescription(String llmResponse) {
        return "";
    }
    private String getBookmarkPrefillCategory(String llmResponse) {
        return "";
    }
}
