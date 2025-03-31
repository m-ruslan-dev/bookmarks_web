package bookmarks.services;

import bookmarks.DTO.BookmarkPrefillDTO;
import bookmarks.DTO.LLMMessagesDTO;
import bookmarks.DTO.LLMRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
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
    String llm_url = "https://api.mistral.ai/v1/chat/completions";

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
    private String getLLMResponseMessage(String llmResponseJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(llmResponseJson);
            // Navigate to choices[0].message.content
            JsonNode choicesNode = rootNode.path("choices");
            if (choicesNode.isArray() && !choicesNode.isEmpty()) {
                JsonNode messageNode = choicesNode.get(0).path("message");
                return messageNode.path("content").asText();
            } else {
                throw new IllegalArgumentException("No choices found in LLM response");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse LLM response", e);
        }
    }

    // Method is responsible for getting the bookmark description and category
    public BookmarkPrefillDTO generateBookmarkPrefill(BookmarkPrefillDTO bookmarkPrefillDTO, String url) {
        LLMRequestDTO llmRequestDTO = new LLMRequestDTO();

        // Construct a JSON object with LLM messages
        List<LLMMessagesDTO> messages = new ArrayList<>();
        // Add instructions message for generating prefill data
        messages.add(new LLMMessagesDTO("system", "You work for a bookmarking app. The user will provide a link to the webpage and a title. Your task is to generate a short description of no more than 3 sentences of that webpage based on the link and the title. You should also come up with a category for that webpage. Aim for a short and concise description solely for the purpose of describing the page's content. Only answer with a description and a title, do not say anything else. Always answer in a JSON format, with the \"description\" key for the description, and the \"title\" key for the title.\n" +
                "Example:\n" +
                "User: \"Webpage URL: 'https://en.wikipedia.org/wiki/History_of_artificial_intelligence'. Webpage title: 'History of artificial intelligence'.\"\n" +
                "You: '{\"description\": \"Wikipedia page on the history of artificial intelligence\", \"category\": \"History\"}'."));
        // Provide relevant prompt to generate data
        messages.add(new LLMMessagesDTO("user", "Webpage URL: '" + url + "'. Webpage title: '" + bookmarkPrefillDTO.getTitle() + "'."));

        // Construct a JSON with model and messages
        llmRequestDTO.setModel("mistral-large-latest");
        llmRequestDTO.setMessages(messages);

        String LLMresponse = performApiCall(llm_url, llmRequestDTO);

        String description = getBookmarkPrefillDescription(LLMresponse);
        String category = getBookmarkPrefillCategory(LLMresponse);

        bookmarkPrefillDTO.setDescription(description);
        bookmarkPrefillDTO.setCategory(category);

        return bookmarkPrefillDTO;
    }

    private String getBookmarkPrefillDescription(String llmResponseMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(llmResponseMessage);
            return rootNode.path("description").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse LLM response message", e);
        }
    }
    private String getBookmarkPrefillCategory(String llmResponseMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(llmResponseMessage);
            return rootNode.path("category").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse LLM response message", e);
        }
    }
}
