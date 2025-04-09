package bookmarks.postgres.repositories;

import bookmarks.postgres.models.BookmarkEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkEmbeddingRepository extends JpaRepository<BookmarkEmbedding, Long> {
}
