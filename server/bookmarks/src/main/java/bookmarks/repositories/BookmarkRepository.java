package bookmarks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import bookmarks.models.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUser_Id(Long userId);

}
