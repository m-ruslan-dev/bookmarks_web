package bookmarks.services;

import bookmarks.DTO.BookmarkPrefillDTO;
import bookmarks.interfaces.IWebpageMetadataInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebsiteMetadataService {

    public String fetchMetadata(String url) {
        WebClient client = WebClient
                .builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(10 * 1024 * 1024)) // 10 mb buffer memory
                .build();

        return client
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new RuntimeException("Failed to fetch metadata. Error: " + response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Failed to fetch metadata. Error: " + response.statusCode())))
                .bodyToMono(String.class)
                .block();
    }

    // Method takes and returns an object that must have setTitle() and setIconUrl() methods
    public <T extends IWebpageMetadataInterface> IWebpageMetadataInterface getMetadata(String url, T metadata) {
        String html = fetchMetadata(url);
        Document doc = Jsoup.parse(html);
        String title = doc.title();
        Element iconLinkElement = doc.selectFirst("link[rel~=(?i)icon]"); // look for the first <link> element with rel attribute containing "icon" string
        String iconUrl = (iconLinkElement != null) ? url + iconLinkElement.attr("href") : null; // Return an icon URL or null

        metadata.setTitle(title);
        metadata.setIconUrl(iconUrl);

        return metadata;
    }
}
